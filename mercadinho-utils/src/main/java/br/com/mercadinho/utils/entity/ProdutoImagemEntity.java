package br.com.mercadinho.utils.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto_imagem")
public class ProdutoImagemEntity {

	@Id
	@SequenceGenerator(name = "SE_PRODUTO_IMAGEM", sequenceName = "SE_PRODUTO_IMAGEM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SE_PRODUTO_IMAGEM")
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "nome", length = 1000, nullable = false)
	private String nome;

	@Column(name = "id_produto", nullable = false)
	private Long idProduto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

}
