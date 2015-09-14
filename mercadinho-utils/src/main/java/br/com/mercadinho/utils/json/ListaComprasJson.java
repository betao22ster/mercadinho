package br.com.mercadinho.utils.json;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * Classe que vai ser usada para transportar dados entre back-end e o front-end. 
 * Não usar diretamente a entidade para evitar erros em caso de objeto carregado com dados
 * associados a proxys de hibernate. 
 * 
 * @author marcelo
 *
 */
public class ListaComprasJson {

	private Long id;

	@NotNull(message = "Nome é obrigatório")
	@NotEmpty(message = "Nome é obrigatório")
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
