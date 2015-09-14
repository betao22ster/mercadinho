package br.com.mercadinho.utils.factory;

import br.com.mercadinho.utils.entity.ProdutoImagemEntity;
import br.com.mercadinho.utils.json.ProdutoImagemJson;

public class ProdutoImagemFactory {

	public static ProdutoImagemJson create(ProdutoImagemEntity imagem) {
		
		ProdutoImagemJson json = new ProdutoImagemJson();
		json.setArquivo(imagem.getNome());
		json.setMiniatura("mini_".concat(imagem.getNome()));
		json.setIdProduto(imagem.getIdProduto());
		
		return json;
	}

}
