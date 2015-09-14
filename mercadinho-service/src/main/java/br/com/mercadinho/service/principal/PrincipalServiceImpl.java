package br.com.mercadinho.service.principal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadinho.repositorio.lista.ListaComprasProdutoRepositorio;
import br.com.mercadinho.repositorio.lista.ListaComprasRepositorio;
import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.factory.ProdutoFactory;
import br.com.mercadinho.utils.json.PrincipalListaJson;

import com.google.common.collect.Lists;

@Service
public class PrincipalServiceImpl implements PrincipalService {

	private static final Logger LOG = Logger.getLogger(PrincipalServiceImpl.class.getSimpleName());
	
	private final ListaComprasRepositorio listaComprasRepositorio; 
	private final ListaComprasProdutoRepositorio listaComprasProdutoRepositorio;
	
	@Autowired
	public PrincipalServiceImpl(ListaComprasRepositorio listaComprasRepositorio, ListaComprasProdutoRepositorio listaComprasProdutoRepositorio) {
		this.listaComprasRepositorio = listaComprasRepositorio;
		this.listaComprasProdutoRepositorio = listaComprasProdutoRepositorio;
	}
	
	@Override
	public List<PrincipalListaJson> carregarLista(){
		
		LOG.info("Carrehando a lista de compras...");
		
		List<ListaComprasEntity> lista = Lists.newArrayList(listaComprasRepositorio.findAll());
		
		if( lista == null ){
			return new ArrayList<PrincipalListaJson>();
		}
		
		List<PrincipalListaJson> nLista = new ArrayList<PrincipalListaJson>();
		
		for (ListaComprasEntity listaComprasEntity : lista) {
			PrincipalListaJson json = new PrincipalListaJson();
			json.setNome(listaComprasEntity.getNome());
			
			BigDecimal calc = BigDecimal.ZERO; 
			
			LOG.info("Carregando os produtos relacionados a lista de compra....");
			List<ProdutoEntity> produtos = listaComprasProdutoRepositorio.findProdutos(listaComprasEntity.getId());
			if( produtos != null ){
				json.setProdutos(ProdutoFactory.create(produtos));
				
				for (ProdutoEntity produto : produtos) {
					if( produto.getValor() != null ){
						calc = calc.add(produto.getValor());
					}
				}
			}
			
			json.setTotal(calc);
			
			nLista.add(json);
		}
		
		return nLista;
	}
	
	
}
