package br.com.mercadinho.utils.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lista_compras")
public class ListaComprasEntity {

	@Id
	@SequenceGenerator(name = "SE_LISTA_COMPRAS", sequenceName = "SE_LISTA_COMPRAS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SE_LISTA_COMPRAS")
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;

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

}
