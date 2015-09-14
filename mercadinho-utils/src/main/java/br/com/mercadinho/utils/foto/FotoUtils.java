package br.com.mercadinho.utils.foto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FotoUtils {

	public static final String PASTA_UPLOAD = "pasta.upload";
	private static final String MINI = "mini_";
	private static final String FORMAT = "ddmmyyyyhhmmss";
	
	private String nomeOriginal;
	private String nomeArquivo;
	public String getNomeArquivo() {
		return nomeArquivo;
	}

	private String pastaFotosProduto;
	private Long idProduto;
	private String pathFoto;
	private String pathMini;
	private String pastaImagens;

	public FotoUtils(String nomeOriginal, Long idProduto, String pastaImagens) {
		this.nomeOriginal = nomeOriginal;
		this.idProduto = idProduto;
		this.pastaImagens = pastaImagens;
		
		createNome();
		createPastaProduto();
		createCaminhoFoto();
		createCaminhoFotoMini();
	}

	public static String getPathFoto(String nomeOriginal, Long idProduto, String pastaImagens){
		
		String ext = "";
		if( nomeOriginal.indexOf(".jpg") < 0 ){
			ext = ".jpg";
		}
		
		return pastaImagens.concat("/").concat(idProduto.toString()).concat("/").concat(nomeOriginal).concat(ext);
	}
	
	private void createCaminhoFotoMini() {
		pathMini = pastaFotosProduto.concat("/").concat(MINI).concat(nomeArquivo);
	}

	private void createCaminhoFoto() {
		pathFoto = pastaFotosProduto.concat("/").concat(nomeArquivo);
	}

	private void createPastaProduto() {
		pastaFotosProduto = pastaImagens.concat("/").concat(idProduto.toString());		
	}

	private void createNome() {
		nomeArquivo = new SimpleDateFormat(FORMAT).format(new Date()).concat(nomeOriginal);
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public String getPathMini() {
		return pathMini;
	}
	
	public String getPastaFotosProduto(){
		return pastaFotosProduto;
	}
}
