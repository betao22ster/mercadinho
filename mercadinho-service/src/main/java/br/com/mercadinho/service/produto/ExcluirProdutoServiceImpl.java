package br.com.mercadinho.service.produto;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.repositorio.produto.ProdutoImagemRepositorio;
import br.com.mercadinho.repositorio.produto.ProdutoRepositorio;
import br.com.mercadinho.utils.entity.ProdutoEntity;
import br.com.mercadinho.utils.exception.RegraNegocioException;

@Service
public class ExcluirProdutoServiceImpl implements ExcluirProdutoService{

	private static final Logger LOG = Logger
			.getLogger(ExcluirProdutoServiceImpl.class.getSimpleName());

	private final ProdutoRepositorio produtoRepositorio;
	private final ProdutoImagemRepositorio produtoImagemRepositorio;
	
	@Autowired
	public ExcluirProdutoServiceImpl(ProdutoRepositorio produtoRepositorio, ProdutoImagemRepositorio produtoImagemRepositorio) {
		this.produtoRepositorio = produtoRepositorio;
		this.produtoImagemRepositorio = produtoImagemRepositorio;
	}
	
	/**
	 * Método principal que vai excluir o produto
	 */
	@Transactional
	@Override
	public void excluir(ProdutoEntity produto) throws RegraNegocioException {

		LOG.info("Validando produto... ");
		validar(produto);

		produtoImagemRepositorio.excluirFoto(produto.getId());
		
		LOG.info("Validação OK, excluindo o produto... ");
		produtoRepositorio.delete(produto.getId());
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

		if (produto == null || produto.getId() == null) {
			LOG.warning("Produto é obrigatório.");
			throw new RegraNegocioException("Produto é obrigatório.");
		}

	}
	
}
