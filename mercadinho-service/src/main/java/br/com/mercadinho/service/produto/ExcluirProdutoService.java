package br.com.mercadinho.service.produto;

import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

public interface ExcluirProdutoService {

	void excluir(ProdutoEntity produto) throws RegraNegocioException;

}
