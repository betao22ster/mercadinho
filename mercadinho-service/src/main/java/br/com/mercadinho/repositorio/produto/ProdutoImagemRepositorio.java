package br.com.mercadinho.repositorio.produto;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.mercadinho.utils.entity.ProdutoImagemEntity;

@Repository
public interface ProdutoImagemRepositorio extends CrudRepository<ProdutoImagemEntity, Long>{

	@Query(value="from ProdutoImagemEntity obj where obj.idProduto = ?1 ")
	List<ProdutoImagemEntity> findImagens(Long idProduto);
	
	@Modifying
	@Query(value="delete from ProdutoImagemEntity where idProduto = ?1 ")
	int excluirFoto(Long idProduto);
}
