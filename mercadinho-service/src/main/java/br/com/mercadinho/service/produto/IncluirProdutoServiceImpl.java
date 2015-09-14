package br.com.mercadinho.service.produto;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.mercadinho.repositorio.produto.ProdutoRepositorio;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

@Service
public class IncluirProdutoServiceImpl implements IncluirProdutoService {

	private static final Logger LOG = Logger
			.getLogger(IncluirProdutoServiceImpl.class.getSimpleName());

	private final ProdutoRepositorio produtoRepositorio;

	@Autowired
	public IncluirProdutoServiceImpl(ProdutoRepositorio produtoRepositorio) {
		this.produtoRepositorio = produtoRepositorio;
	}

	/**
	 * Método principal que vai incluir o produto
	 */
	@Transactional
	@Override
	public void incluir(ProdutoEntity produto) throws RegraNegocioException {

		LOG.info("Validando produto... ");
		validar(produto);

		LOG.info("Validação OK, gravando produto... ");
		produtoRepositorio.save(produto);
	}

	/**
	 * 
	 * Método que faz a validação do produto.<br>
	 * Se já existe um produto com o mesmo nome.
	 * 
	 * @param produto
	 * @throws RegraNegocioException
	 */
	private void validar(ProdutoEntity produto) throws RegraNegocioException {

		if (produto == null || StringUtils.isEmpty(produto.getNome())) {
			LOG.warning("Produto é obrigatório.");
			throw new RegraNegocioException("Produto é obrigatório.");
		}

		if (produtoRepositorio.getNroProdutosNome(produto.getNome()) > 0) {
			LOG.warning("O nome do produto já existe - " + produto.getNome());
			throw new RegraNegocioException("O nome do produto já existe.");
		}

	}

}
