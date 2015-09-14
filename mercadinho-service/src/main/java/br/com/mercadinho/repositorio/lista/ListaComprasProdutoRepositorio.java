package br.com.mercadinho.repositorio.lista;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadinho.utils.entity.ListaComprasProdutoEntity;
import br.com.mercadinho.utils.entity.ProdutoEntity;

@Repository
public interface ListaComprasProdutoRepositorio  extends CrudRepository<ListaComprasProdutoEntity, Long>{

	@Query("select prod from ListaComprasProdutoEntity obj inner join obj.produto prod where obj.listaCompras.id = ?1 ")
	List<ProdutoEntity> findProdutos(Long idLista);
}
