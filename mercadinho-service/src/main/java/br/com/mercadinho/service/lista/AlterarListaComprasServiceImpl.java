package br.com.mercadinho.service.lista;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.repositorio.lista.ListaComprasRepositorio;
import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

@Service
public class AlterarListaComprasServiceImpl implements AlterarListaComprasService {

	private static final Logger LOG = Logger
			.getLogger(AlterarListaComprasServiceImpl.class.getSimpleName());

	
	private final ListaComprasRepositorio listaComprasRepositorio;
	
	@Autowired
	public AlterarListaComprasServiceImpl(ListaComprasRepositorio listaComprasRepositorio) {
		this.listaComprasRepositorio = listaComprasRepositorio;
	}
	
	@Transactional
	@Override
	public void alterar(ListaComprasEntity listaCompras) throws RegraNegocioException {

		LOG.info("Validação OK, gravando.. ");
		listaComprasRepositorio.alterar(listaCompras.getNome(), listaCompras.getId());
	}

}
