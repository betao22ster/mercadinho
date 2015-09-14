package br.com.mercadinho.repositorio.lista;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadinho.utils.entity.ListaComprasEntity;

@Repository
public interface ListaComprasRepositorio  extends CrudRepository<ListaComprasEntity, Long>{

	@Modifying
	@Query("update ListaComprasEntity obj set obj.nome = ?1 where obj.id = ?2")
	int alterar(String nome, Long id);
	
	@Modifying
	@Query("delete from ListaComprasProdutoEntity where idLista = ?1")
	void deleteListaProdutos(Long idLista);
}
