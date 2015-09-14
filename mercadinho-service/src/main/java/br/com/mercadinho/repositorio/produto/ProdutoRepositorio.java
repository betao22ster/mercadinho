package br.com.mercadinho.repositorio.produto;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadinho.utils.entity.ProdutoEntity;

@Repository
public interface ProdutoRepositorio extends CrudRepository<ProdutoEntity, Long>{

	@Query(value="select count(obj.id) from ProdutoEntity obj where upper(obj.nome) = upper(?1) ")
	Long getNroProdutosNome(String nome);

	@Query(value="select count(obj.id) from ProdutoEntity obj where upper(obj.nome) = upper(?1) and obj.id <> ?2 ")
	Long getNroProdutosNomeMenosId(String nome, Long id);

	@Modifying
	@Query("update ProdutoEntity obj set obj.nome = ?1, obj.valor = ?3 where obj.id = ?2")
	int alterar(String nome, Long id, BigDecimal valor);
	
}
