package br.com.inottec.cdv.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itemPedido")
public class ItemPedido  implements RestricaoEntidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_item")
	private Long codigo;
	
	@Column(name="codigo_produto")
	private Long codigoProduto;	
	private String descricao ;
	private double preco ;
	@Column(name="qtd_estoque")
	private int qtdEstoque;
	private double subtotal;	
	
	@ManyToOne
	private Pedido pedido;


	public ItemPedido() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public ItemPedido(Long codigoProduto, String descricao, double preco, int qtdEstoque, double subtotal, Pedido pedido) {
		super();
		this.codigoProduto = codigoProduto;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.subtotal = subtotal;
		this.pedido = pedido;
	}


	public Long getCodigo() {
		return codigoProduto;
	}


	public void setCodigo(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public int getQtdEstoque() {
		return qtdEstoque;
	}


	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}


	public double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	@Override
	public String toString() {
		return "ItemPedido [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", qtdEstoque="
				+ qtdEstoque + ", subtotal=" + subtotal + ", pedido=" + pedido + "]";
	} 
	
	
}
