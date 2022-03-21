package br.com.inottec.cdv.modelo;

public class OpcoesUF implements RestricaoEntidade{

	private String estado;

	public OpcoesUF(String estado) {
		super();
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return getEstado();
	}

}
