package br.com.mercadinho.utils.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.json.ProdutoJson;

/**
 * 
 * A classe factory para converter uma entity em um json é para manter o baixo acoplamento.
 * 
 * @author marcelo
 *
 */
public class ProdutoFactory {

	public static ProdutoJson create(ProdutoEntity produto){
		
		ProdutoJson json = new ProdutoJson();
		json.setId(produto.getId());
		json.setNome(produto.getNome());
		json.setValor(produto.getValor());
		
		return json;
	}
	
	public static ProdutoEntity create(ProdutoJson json){
		
		ProdutoEntity produto = new ProdutoEntity();
		produto.setId(json.getId());
		produto.setNome(json.getNome());
		produto.setValor(json.getValor());
		
		return produto;
	}

	public static List<ProdutoJson> create(List<ProdutoEntity> lista) {
		
		List<ProdutoJson> nLista = new ArrayList<ProdutoJson>();
		if( lista == null ){
			return nLista;
		}
		
		for (ProdutoEntity produto : lista) {
			nLista.add(create(produto));
		}
		
		return nLista;
	}
}
