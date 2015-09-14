package br.com.mercadinho.service.lista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mercadinho.repositorio.lista.ListaComprasProdutoRepositorio;
import br.com.mercadinho.utils.entity.ListaComprasProdutoEntity;

@Service
public class IncluirProdutoListaComprasServiceImpl implements IncluirProdutoListaComprasService{

	private final ListaComprasProdutoRepositorio listaComprasProdutoRepositorio;

	@Autowired
	public IncluirProdutoListaComprasServiceImpl(ListaComprasProdutoRepositorio listaComprasProdutoRepositorio) {
		this.listaComprasProdutoRepositorio = listaComprasProdutoRepositorio;
	}
	
	@Transactional
	@Override
	public void incluir(Long idLista, Long idProduto){
		
		ListaComprasProdutoEntity novo = new ListaComprasProdutoEntity();
		novo.setIdLista(idLista);
		novo.setIdProduto(idProduto);
		
		listaComprasProdutoRepositorio.save(novo);
	}
	
	
}
