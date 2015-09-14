package br.com.mercadinho.utils.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="produto")
public class ProdutoEntity {

	@Id
	@SequenceGenerator(name = "SE_PRODUTO", sequenceName = "SE_PRODUTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SE_PRODUTO")
	@Column(name = "id", unique = true)
	private Long id;
	
	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

	@Column(name = "valor", precision=12, scale=2, nullable = true)
	private BigDecimal valor;
	
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
