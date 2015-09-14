package br.com.mercadinho.service.lista;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadinho.repositorio.lista.ListaComprasRepositorio;
import br.com.mercadinho.utils.entity.ListaComprasEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

@Service
public class IncluirListaComprasServiceImpl implements IncluirListaComprasService {

	private static final Logger LOG = Logger
			.getLogger(IncluirListaComprasServiceImpl.class.getSimpleName());

	
	private final ListaComprasRepositorio listaComprasRepositorio;
	
	@Autowired
	public IncluirListaComprasServiceImpl(ListaComprasRepositorio listaComprasRepositorio) {
		this.listaComprasRepositorio = listaComprasRepositorio;
	}
	
	@Override
	public void incluir(ListaComprasEntity listaCompras) throws RegraNegocioException {

		LOG.info("Validação OK, gravando.. ");
		listaComprasRepositorio.save(listaCompras);
	}

}
