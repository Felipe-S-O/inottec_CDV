package br.com.inottec.cdv.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido  implements RestricaoEntidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_pedido")
	private Long codigo;
	
	private double pix;
	private double dinheiro;
	private double cartao;
	private double troco;
	@Column(name="valor_pago")
	private double valorPago;
	private Date data;
	private String operador;
	@Column(name="CPF_cliente")
	private String cpfCliente;
	@Column(name="nome_cliente")
	private String nomeCliente;
	
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pedido(double pix, double dinheiro, double cartao, double troco, double valorPago, Date data,
			String operador, String cpfCliente, String nomeCliente) {
		super();
		this.pix = pix;
		this.dinheiro = dinheiro;
		this.cartao = cartao;
		this.troco = troco;
		this.valorPago = valorPago;
		this.data = data;
		this.operador = operador;
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
	}


	public double getPix() {
		return pix;
	}

	public void setPix(double pix) {
		this.pix = pix;
	}

	public double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public double getCartao() {
		return cartao;
	}

	public void setCartao(double cartao) {
		this.cartao = cartao;
	}

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@Override
	public String toString() {
		return "Pedido [pix=" + pix + ", dinheiro=" + dinheiro + ", cartao=" + cartao + ", troco=" + troco
				+ ", valorPago=" + valorPago + ", data=" + data + ", operador=" + operador + ", cpfCliente="
				+ cpfCliente + ", nomeCliente=" + nomeCliente + "]";
	}

	
	
	
	

}
