package br.com.mercadinho.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mercadinho.repositorio.produto.ProdutoImagemRepositorio;
import br.com.mercadinho.service.produto.IncluirFotoProdutoService;
import br.com.mercadinho.utils.entity.ProdutoImagemEntity;
import br.com.mercadinho.utils.exception.GravacaoImagemException;
import br.com.mercadinho.utils.factory.ProdutoImagemFactory;
import br.com.mercadinho.utils.foto.FotoUtils;
import br.com.mercadinho.utils.json.ProdutoImagemJson;

@RestController
@RequestMapping("/fotos")
public class UploadFotosController {

	private final IncluirFotoProdutoService incluirFotoProdutoService;
	private final ProdutoImagemRepositorio produtoImagemRepositorio;
	private Environment environment;
	
	@Autowired
	public UploadFotosController(
			IncluirFotoProdutoService incluirFotoProdutoService, ProdutoImagemRepositorio produtoImagemRepositorio, Environment environment) {
		this.incluirFotoProdutoService = incluirFotoProdutoService;
		this.produtoImagemRepositorio = produtoImagemRepositorio;
		this.environment = environment;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(@RequestParam("id") Long idProduto,
			@RequestParam("file") MultipartFile file)
			throws GravacaoImagemException {

		incluirFotoProdutoService.incluir(idProduto, file);
	}

	@RequestMapping(value = "/findImagens/{idProduto}", method = RequestMethod.GET)
	public List<ProdutoImagemJson> findImagens(@PathVariable Long idProduto){
		
		List<ProdutoImagemJson> nLista = new ArrayList<ProdutoImagemJson>();
		List<ProdutoImagemEntity> findImagens = produtoImagemRepositorio.findImagens(idProduto);
		
		if( findImagens == null ){
			return nLista;
		}
		
		for (ProdutoImagemEntity produto : findImagens) {
			nLista.add(ProdutoImagemFactory.create(produto));
		}
		
		return nLista;
	}
	
	@RequestMapping(value = "/show/{idProduto}/{imagem}", method = RequestMethod.GET)
	public @ResponseBody byte[] show(@PathVariable Long idProduto, @PathVariable String imagem) throws IOException{
			
		return Files.readAllBytes(Paths.get(FotoUtils.getPathFoto(imagem, idProduto, environment.getProperty(FotoUtils.PASTA_UPLOAD))));
		
	}
	
}
