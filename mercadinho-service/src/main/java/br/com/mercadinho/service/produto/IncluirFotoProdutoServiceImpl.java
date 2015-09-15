package br.com.mercadinho.service.produto;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mercadinho.repositorio.produto.ProdutoImagemRepositorio;
import br.com.mercadinho.utils.entity.ProdutoImagemEntity;
import br.com.mercadinho.utils.exception.GravacaoImagemException;
import br.com.mercadinho.utils.foto.FotoUtils;

/**
 * Classe principal para gravação das imagens.
 * Existe uma pasta configurada no application.properties onde vai ser a pasta
 * que vai ser gravada todas as imagens.
 * Essa pasta vai ser organizada por produto. Assim cada imagens vai ser colocada
 * dentro de uma pasta com o id do produto.
 * 
 * @author Mineiro
 *
 */
@Service
public class IncluirFotoProdutoServiceImpl implements IncluirFotoProdutoService {

	private static final Logger LOG = Logger
			.getLogger(IncluirFotoProdutoServiceImpl.class.getSimpleName());
	
	private final Environment environment;
	private final ProdutoImagemRepositorio produtoImagemRepositorio;
	
	@Autowired
	public IncluirFotoProdutoServiceImpl(Environment environment,ProdutoImagemRepositorio produtoImagemRepositorio) {
		this.environment = environment;
		this.produtoImagemRepositorio = produtoImagemRepositorio;
	}
	
	@Override
	public void incluir(Long idProduto, MultipartFile arquivo) throws GravacaoImagemException {
		
		// se não tiver arquivo eu já cancelo.
		if (arquivo == null || arquivo.isEmpty()) {
			LOG.info("Sem arquivos.");
			return;
		}
		
		// Criando classe princia que vai configurar os nomes, pastas e miniatura da imagems.
		FotoUtils foto = new FotoUtils(arquivo.getOriginalFilename(), idProduto, environment.getProperty(FotoUtils.PASTA_UPLOAD));
		
		if( !Files.exists(Paths.get(foto.getPastaFotosProduto())) ){
			try {
				LOG.info("Criando pasta com id do produto.");
				Files.createDirectory(Paths.get(foto.getPastaFotosProduto()));
			} catch (IOException e) {
				LOG.warning("Não criado a pasta, pode ser alguma permissão.");
				throw new GravacaoImagemException("Não foi possível criar a pasta.");
			}
		}
		
		LOG.info("Gravando imagem original...");
		File novoArquivo = gravarImagem(arquivo, foto.getPathFoto());
		
		LOG.info("Gravando miniatura....");
		gravarMiniatura(novoArquivo, foto.getPathMini());
		
		LOG.info("Gravando banco...");
		gravarBanco(idProduto, foto.getNomeArquivo());
	}

	private void gravarBanco(Long idProduto, String nomeArquivo) {
		ProdutoImagemEntity imagem = new ProdutoImagemEntity();
		imagem.setIdProduto(idProduto);
		imagem.setNome(nomeArquivo);
		
		produtoImagemRepositorio.save(imagem);
	}

	/**
	 * 
	 * Métodoo que vai criar uma miniatura da imagem.
	 * 
	 * @param image
	 * @param nomeMiniatura
	 * @throws GravacaoImagemException
	 */
	private void gravarMiniatura(File image, String nomeMiniatura) throws GravacaoImagemException {  
	    try {
	    	
	    	LOG.info("Criando miniatura....");
	        BufferedImage imagem = ImageIO.read(image);  
	        BufferedImage new_img = new BufferedImage(
	        		FotoUtils.TAMANHO_MINIATURA, FotoUtils.TAMANHO_MINIATURA, BufferedImage.TYPE_INT_RGB);  
	        
	        Graphics2D g = new_img.createGraphics();  
	        g.drawImage(imagem, 0, 0, 
	        		FotoUtils.TAMANHO_MINIATURA, FotoUtils.TAMANHO_MINIATURA, null);
	        
	        g.dispose();  
	  
	        ImageIO.write(new_img, FotoUtils.EXTENSAO, new File(nomeMiniatura));
	    } catch (IOException ex) {  
	    	LOG.warning("Erro ao criar a miniatura." + ex.getMessage());
	        throw new GravacaoImagemException(ex.getMessage());  
	    }  
	}  
	
	private File gravarImagem(MultipartFile arquivo, String novo) throws GravacaoImagemException{
		
		File novoArquivo = new File(novo);
		
		try {
            byte[] bytes = arquivo.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(novoArquivo));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            throw new GravacaoImagemException(e.getMessage());
        }
		
		return novoArquivo;
	}

}
