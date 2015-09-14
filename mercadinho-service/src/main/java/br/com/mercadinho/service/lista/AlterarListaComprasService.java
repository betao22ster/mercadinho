package br.com.mercadinho.service.lista;

import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

public interface AlterarListaComprasService {

	void alterar(ListaComprasEntity listaCompras) throws RegraNegocioException;

}
