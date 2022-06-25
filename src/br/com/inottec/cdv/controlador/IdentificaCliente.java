package br.com.inottec.cdv.controlador;

import br.com.inottec.cdv.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class IdentificaCliente {

	@FXML
	private TextField campoBairro;

	@FXML
	private TextField campoCEP;

	@FXML
	private TextField campoCPF;

	@FXML
	private TextField campoCelular;

	@FXML
	private TextField campoCidade;

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoComplemeto;

	@FXML
	private TextField campoEmail;

	@FXML
	private TextField campoEndereco;

	@FXML
	private TextField campoNome;

	@FXML
	private TextField campoNomeConsulta;

	@FXML
	private TextField campoNumero;

	@FXML
	private TextField campoRG;

	@FXML
	private TextField campoTelefone;

	@FXML
	private TableColumn<?, ?> colunaBairro;

	@FXML
	private TableColumn<?, ?> colunaCEP;

	@FXML
	private TableColumn<?, ?> colunaCPF;

	@FXML
	private TableColumn<?, ?> colunaCelular;

	@FXML
	private TableColumn<?, ?> colunaCidade;

	@FXML
	private TableColumn<?, ?> colunaCodigo;

	@FXML
	private TableColumn<?, ?> colunaComple;

	@FXML
	private TableColumn<?, ?> colunaEmail;

	@FXML
	private TableColumn<?, ?> colunaEndereco;

	@FXML
	private TableColumn<?, ?> colunaNome;

	@FXML
	private TableColumn<?, ?> colunaNumero;

	@FXML
	private TableColumn<?, ?> colunaRG;

	@FXML
	private TableColumn<?, ?> colunaTelefone;

	@FXML
	private TableColumn<?, ?> colunaUF;

	@FXML
	private ComboBox<?> comboBoxUF;

	@FXML
	private TableView<?> tabelaClientes;

	@FXML
	private TabPane tabelaPane;

	@FXML
	void botaoEditar(ActionEvent event) {

	}

	@FXML
	void botaoExcluir(ActionEvent event) {

	}

	@FXML
	void botaoIdentificar(ActionEvent event) {

	}

	@FXML
	void botaoLista(ActionEvent event) {

	}

	@FXML
	void botaoVoltar(ActionEvent event) {

		Main.trocaTela("telaPDV");
	}

	@FXML
	void botaoNovo(ActionEvent event) {

	}

	@FXML
	void botaoSalvar(ActionEvent event) {

	}

	@FXML
	void buscaCEP(KeyEvent event) {

	}

	@FXML
	void campoNomePesquisar(KeyEvent event) {

	}

	@FXML
	void carregarSelecao(ActionEvent event) {

	}

	@FXML
	void formataCampoCEP(KeyEvent event) {

	}

	@FXML
	void formataCampoCPF(KeyEvent event) {

	}

	@FXML
	void formataCampoCelular(KeyEvent event) {

	}

	@FXML
	void formataCampoCodigo(KeyEvent event) {

	}

	@FXML
	void formataCampoNumero(KeyEvent event) {

	}

	@FXML
	void formataCampoRG(KeyEvent event) {

	}

	@FXML
	void formataCampoTelefone(KeyEvent event) {

	}

}
