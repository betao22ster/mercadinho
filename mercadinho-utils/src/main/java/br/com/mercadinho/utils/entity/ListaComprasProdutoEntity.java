package br.com.mercadinho.utils.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lista_compras_produto")
public class ListaComprasProdutoEntity {

	@Id
	@SequenceGenerator(name = "SE_LISTA_COMPRAS_PRODUTO", sequenceName = "SE_LISTA_COMPRAS_PRODUTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SE_LISTA_COMPRAS_PRODUTO")
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "idproduto", nullable = false)
	private Long idProduto;

	@Column(name = "idlista", nullable = false)
	private Long idLista;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idproduto", nullable = true, insertable=false, updatable=false)
	private ProdutoEntity produto;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idlista", nullable = true, insertable=false, updatable=false)
	private ListaComprasEntity listaCompras;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdLista() {
		return idLista;
	}

	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public ListaComprasEntity getListaCompras() {
		return listaCompras;
	}

	public void setListaCompras(ListaComprasEntity listaCompras) {
		this.listaCompras = listaCompras;
	}

	
}
