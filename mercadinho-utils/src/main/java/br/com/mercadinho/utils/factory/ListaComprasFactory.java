package br.com.mercadinho.utils.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.json.ListaComprasJson;

/**
 * 
 * A classe factory para converter uma entity em um json é para manter o baixo acoplamento.
 * 
 * @author marcelo
 *
 */
public class ListaComprasFactory {

	public static ListaComprasJson create(ListaComprasEntity listaCompras){
		
		ListaComprasJson json = new ListaComprasJson();
		json.setId(listaCompras.getId());
		json.setNome(listaCompras.getNome());
		
		return json;
	}
	
	public static ListaComprasEntity create(ListaComprasJson json){
		
		ListaComprasEntity listaCompras = new ListaComprasEntity();
		listaCompras.setId(json.getId());
		listaCompras.setNome(json.getNome());
		
		return listaCompras;
	}

	public static List<ListaComprasJson> create(List<ListaComprasEntity> lista) {
		
		List<ListaComprasJson> nLista = new ArrayList<ListaComprasJson>();
		if( lista == null ){
			return nLista;
		}
		
		for (ListaComprasEntity listaCompra : lista) {
			nLista.add(create(listaCompra));
		}
		
		return nLista;
	}
}
