package br.com.mercadinho.service.lista;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.repositorio.lista.ListaComprasRepositorio;
import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

@Service
public class ExcluirListaComprasServiceImpl implements ExcluirListaComprasService {

	private static final Logger LOG = Logger
			.getLogger(ExcluirListaComprasServiceImpl.class.getSimpleName());

	
	private final ListaComprasRepositorio listaComprasRepositorio;
	
	@Autowired
	public ExcluirListaComprasServiceImpl(ListaComprasRepositorio listaComprasRepositorio) {
		this.listaComprasRepositorio = listaComprasRepositorio;
	}
	
	@Transactional
	@Override
	public void excluir(ListaComprasEntity listaCompras) throws RegraNegocioException {

		listaComprasRepositorio.deleteListaProdutos(listaCompras.getId());
		
		LOG.info("Validação OK, gravando.. ");
		listaComprasRepositorio.delete(listaCompras.getId());
	}

}
