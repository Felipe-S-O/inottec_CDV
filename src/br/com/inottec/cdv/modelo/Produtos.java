 package br.com.inottec.cdv.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javafx.scene.control.Button;


@Entity
@Table(name = "tb_produto")
public class Produtos implements RestricaoEntidade{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String descricao ;
	private double preco ;
	private int qtdEstoque;
	
	@OneToOne
	private Fornecedores fornecedor; 
	
	@Transient
	private double subtotal;
	
	@Transient
	private Button excluir;
	
	
	
	//==== construto =====	
	public Produtos() {
		super();
	
		// TODO Auto-generated constructor stub
	}
	
	public Produtos(String descricao, double preco, int qtdEstoque, Fornecedores fornecedor) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.fornecedor = fornecedor;
	}	
	
	public Produtos(Long codigo, String descricao, double preco, int qtdEstoque, Fornecedores fornecedor,
			double subtotal) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.fornecedor = fornecedor;
		this.subtotal = subtotal;
	}

	//==== get e set =====	
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Button getExcluir() {
		return excluir;
	}
	
	public void setExcluir(Button excluir) {
		this.excluir = excluir;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Fornecedores getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public String toString() {
		return "Produtos [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", qtdEstoque="
				+ qtdEstoque + ", fornecedor=" + fornecedor + "]";
	}
	
}
