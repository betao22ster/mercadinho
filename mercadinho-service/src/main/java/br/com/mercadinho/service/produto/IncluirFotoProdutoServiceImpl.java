package br.com.mercadinho.service.produto;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mercadinho.repositorio.produto.ProdutoImagemRepositorio;
import br.com.mercadinho.utils.entity.ProdutoImagemEntity;
import br.com.mercadinho.utils.exception.GravacaoImagemException;
import br.com.mercadinho.utils.foto.FotoUtils;

@Service
public class IncluirFotoProdutoServiceImpl implements IncluirFotoProdutoService {

	private final Environment environment;
	private final ProdutoImagemRepositorio produtoImagemRepositorio;
	
	@Autowired
	public IncluirFotoProdutoServiceImpl(Environment environment,ProdutoImagemRepositorio produtoImagemRepositorio) {
		this.environment = environment;
		this.produtoImagemRepositorio = produtoImagemRepositorio;
	}
	
	@Override
	public void incluir(Long idProduto, MultipartFile arquivo) throws GravacaoImagemException {
		
		if (arquivo.isEmpty()) {
			return;
		}
		
		FotoUtils foto = new FotoUtils(arquivo.getOriginalFilename(), idProduto, environment.getProperty(FotoUtils.PASTA_UPLOAD));
		
		if( !Files.exists(Paths.get(foto.getPastaFotosProduto())) ){
			try {
				Files.createDirectory(Paths.get(foto.getPastaFotosProduto()));
			} catch (IOException e) {
				throw new GravacaoImagemException("Não foi possível criar a pasta.");
			}
		}
		
		File novoArquivo = gravarImagem(arquivo, foto.getPathFoto());
		
		gravarMiniatura(novoArquivo, foto.getPathMini());
		
		gravarBanco(idProduto, foto.getNomeArquivo());
	}

	private void gravarBanco(Long idProduto, String nomeArquivo) {
		ProdutoImagemEntity imagem = new ProdutoImagemEntity();
		imagem.setIdProduto(idProduto);
		imagem.setNome(nomeArquivo);
		
		produtoImagemRepositorio.save(imagem);
	}

	private void gravarMiniatura(File image, String nomeMiniatura) throws GravacaoImagemException {  
	    try {  
	        BufferedImage imagem = ImageIO.read(image);  
	        BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);  
	        
	        Graphics2D g = new_img.createGraphics();  
	        g.drawImage(imagem, 0, 0, 50, 50, null);  
	        g.dispose();  
	  
	        ImageIO.write(new_img, "jpg", new File(nomeMiniatura));
	    } catch (IOException ex) {  
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
