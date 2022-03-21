package br.com.inottec.cdv.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionarios implements RestricaoEntidade{

	//Atributos
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long codigo;
		
		private String nome;
		private String rg;
		private String cpf;
		private String email;
		private String senha;
		private String cargo;
		private String nivelDeAcesso;
		private String telefone;
		private String celular;
		private String cep;
		private String endereco;
		private String numero;
		private String complemento;
		private String bairro;
		private String cidade;
		private String estado;
		
		public Funcionarios(String nome, String rg, String cpf, String email, String senha, String cargo,
				String nivelDeAcesso, String telefone, String celular, String cep, String endereco, String numero,
				String complemento, String bairro, String cidade, String estado) {
			super();
			this.nome = nome;
			this.rg = rg;
			this.cpf = cpf;
			this.email = email;
			this.senha = senha;
			this.cargo = cargo;
			this.nivelDeAcesso = nivelDeAcesso;
			this.telefone = telefone;
			this.celular = celular;
			this.cep = cep;
			this.endereco = endereco;
			this.numero = numero;
			this.complemento = complemento;
			this.bairro = bairro;
			this.cidade = cidade;
			this.estado = estado;
		}
		public Funcionarios() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Long getCodigo() {
			return codigo;
		}
		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getRg() {
			return rg;
		}
		public void setRg(String rg) {
			this.rg = rg;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public String getCargo() {
			return cargo;
		}
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
		public String getNivelDeAcesso() {
			return nivelDeAcesso;
		}
		public void setNivelDeAcesso(String nivelDeAcesso) {
			this.nivelDeAcesso = nivelDeAcesso;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getCelular() {
			return celular;
		}
		public void setCelular(String celular) {
			this.celular = celular;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public String getComplemento() {
			return complemento;
		}
		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}
		public String getBairro() {
			return bairro;
		}
		public void setBairro(String bairro) {
			this.bairro = bairro;
		}
		public String getCidade() {
			return cidade;
		}
		public void setCidade(String cidade) {
			this.cidade = cidade;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
}
