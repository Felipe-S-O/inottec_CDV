package br.com.inottec.cdv.modelo;

import org.apache.log4j.Logger;

public class CEP {

	 private String endereco;
	 private String cidade;
	 private String bairro;
	 private String uf;
	 
	// criando um logger
		private static Logger logger = Logger.getLogger(CEP.class);


	public CEP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public  void setUf(String uf) {
		this.uf = uf;
	}

	public void buscaCep(String cep) {

		WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

		if (webServiceCep.wasSuccessful()) {
			endereco = webServiceCep.getLogradouroFull();
			cidade = webServiceCep.getCidade();
			bairro = webServiceCep.getBairro();
			uf = webServiceCep.getUf();

			logger.info("Endereço encotrado com sucesso!");
		}else {
			logger.info("Endereço ou não encotrado ");
		}
	}


}
