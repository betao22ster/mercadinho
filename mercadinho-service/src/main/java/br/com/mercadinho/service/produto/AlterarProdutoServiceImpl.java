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
public class AlterarProdutoServiceImpl implements AlterarProdutoService {

	private static final Logger LOG = Logger.getLogger(AlterarProdutoServiceImpl.class.getSimpleName());
	
	private final ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	public AlterarProdutoServiceImpl(ProdutoRepositorio produtoRepositorio) {
		this.produtoRepositorio = produtoRepositorio;
	}
	
	/**
	 * Método principal que vai alterar o produto
	 */
	@Transactional
	@Override
	public void alterar(ProdutoEntity produto) throws RegraNegocioException{
		
		LOG.info("Validando produto... ");
		validar(produto);
		
		LOG.info("Validação OK, gravando produto... ");
		produtoRepositorio.alterar(produto.getNome(), produto.getId(), produto.getValor());
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
		
		if( produto == null || produto.getId() == null || StringUtils.isEmpty(produto.getNome()) ){
			LOG.warning("Produto é obrigatório.");
			throw new RegraNegocioException("Produto é obrigatório.");
		}
		
		if( produtoRepositorio.getNroProdutosNomeMenosId(produto.getNome(), produto.getId()) > 0 ){
			LOG.warning("O nome do produto já existe - "+ produto.getNome());
			throw new RegraNegocioException("O nome do produto já existe.");
		}
		
	}
	
}
