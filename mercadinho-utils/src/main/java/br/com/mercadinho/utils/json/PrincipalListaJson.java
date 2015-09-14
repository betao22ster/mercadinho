package br.com.mercadinho.utils.json;

import java.math.BigDecimal;
import java.util.List;

public class PrincipalListaJson {

	private String nome;
	private List<ProdutoJson> produtos;
	private BigDecimal total;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProdutoJson> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoJson> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
