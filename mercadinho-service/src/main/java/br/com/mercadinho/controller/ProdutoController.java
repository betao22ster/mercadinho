package br.com.mercadinho.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.repositorio.produto.ProdutoRepositorio;
import br.com.mercadinho.service.produto.AlterarProdutoService;
import br.com.mercadinho.service.produto.ExcluirProdutoService;
import br.com.mercadinho.service.produto.IncluirProdutoService;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.factory.ProdutoFactory;
import br.com.mercadinho.utils.json.ProdutoJson;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private IncluirProdutoService incluirProdutoService;
	
	@Autowired
	private AlterarProdutoService alterarProdutoService;
	
	@Autowired
	private ExcluirProdutoService excluirProdutoService;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	public ProdutoJson incluir(@Valid @RequestBody ProdutoJson json) throws Exception {
		
		ProdutoEntity produto = ProdutoFactory.create(json);
		incluirProdutoService.incluir(produto);

		// Objetivo de retornar o objeto e que caso o gravação tenha sido tudo ok, ele já retorna o ID gerado.
		return ProdutoFactory.create(produto);
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ProdutoJson alterar(@Valid @RequestBody ProdutoJson json) throws Exception {
		
		ProdutoEntity produto = ProdutoFactory.create(json);
		alterarProdutoService.alterar(produto);

		return ProdutoFactory.create(produto);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<ProdutoJson> findAll() throws Exception {
		
		List<ProdutoEntity> lista = Lists.newArrayList(produtoRepositorio.findAll());
		return ProdutoFactory.create(lista);
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public void excluir(@Valid @RequestBody ProdutoJson json) throws Exception {
		
		ProdutoEntity produto = ProdutoFactory.create(json);
		excluirProdutoService.excluir(produto);
	}
}
