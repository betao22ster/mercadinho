package br.com.mercadinho.service.lista;

import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

public interface ExcluirListaComprasService {

	void excluir(ListaComprasEntity listaCompras) throws RegraNegocioException;


}
