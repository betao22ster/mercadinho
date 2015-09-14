package br.com.mercadinho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.repositorio.lista.ListaComprasProdutoRepositorio;
import br.com.mercadinho.repositorio.lista.ListaComprasRepositorio;
import br.com.mercadinho.service.lista.AlterarListaComprasService;
import br.com.mercadinho.service.lista.ExcluirListaComprasService;
import br.com.mercadinho.service.lista.IncluirListaComprasService;
import br.com.mercadinho.service.lista.IncluirProdutoListaComprasService;
import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.factory.ListaComprasFactory;
import br.com.mercadinho.utils.factory.ProdutoFactory;
import br.com.mercadinho.utils.json.ListaComprasJson;
import br.com.mercadinho.utils.json.ProdutoJson;

import com.google.common.collect.Lists;

@RestController
@RequestMapping("/lcompras")
public class ListaComprasController {

	@Autowired
	private IncluirListaComprasService incluirListaComprasService;

	@Autowired
	private AlterarListaComprasService alterarListaComprasService;

	@Autowired
	private ExcluirListaComprasService excluirListaComprasService;

	@Autowired
	private ListaComprasRepositorio listaComprasRepositorio;

	@Autowired
	private IncluirProdutoListaComprasService incluirProdutoListaComprasService;
	
	@Autowired
	private ListaComprasProdutoRepositorio listaComprasProdutoRepositorio;
	
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	public ListaComprasJson incluir(@Valid @RequestBody ListaComprasJson json)
			throws Exception {

		ListaComprasEntity lCompras = ListaComprasFactory.create(json);
		incluirListaComprasService.incluir(lCompras);

		return ListaComprasFactory.create(lCompras);
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ListaComprasJson alterar(@Valid @RequestBody ListaComprasJson json)
			throws Exception {

		ListaComprasEntity lCompras = ListaComprasFactory.create(json);
		alterarListaComprasService.alterar(lCompras);

		return ListaComprasFactory.create(lCompras);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<ListaComprasJson> findAll() throws Exception {

		List<ListaComprasEntity> lista = Lists
				.newArrayList(listaComprasRepositorio.findAll());
		return ListaComprasFactory.create(lista);
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.POST)
	public void excluir(@Valid @RequestBody ListaComprasJson json)
			throws Exception {

		ListaComprasEntity lCompras = ListaComprasFactory.create(json);
		excluirListaComprasService.excluir(lCompras);
	}
	
	@RequestMapping(value = "/incluirprod/{idProduto}/{idLista}", method = RequestMethod.GET)
	public void incluirProduto(@PathVariable Long idProduto, @PathVariable Long idLista){
		incluirProdutoListaComprasService.incluir(idLista, idProduto);
	}
	
	@RequestMapping(value = "/findallprod/{idLista}", method = RequestMethod.GET)
	public List<ProdutoJson> findAllProdutos(@PathVariable Long idLista){

		List<ProdutoEntity> produtos = listaComprasProdutoRepositorio.findProdutos(idLista);
		
		if( produtos == null ){
			return new ArrayList<ProdutoJson>();
		}
		
		List<ProdutoJson> nlista = ProdutoFactory.create(produtos);
		
		return nlista;
	}
	
	
	
	
	
	
}
