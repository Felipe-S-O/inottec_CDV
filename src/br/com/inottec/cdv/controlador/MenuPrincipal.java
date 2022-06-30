package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import br.com.inottec.cdv.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuPrincipal implements Initializable {

	@FXML
	private Label data;

	@FXML	
	private Label operador;

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		
		dataAtual();
		
		operador.setText(TelaLogin.operador);

	}
	
	private void dataAtual() {
		 DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    data.setText(dtf4.format(LocalDateTime.now()));
	}

	@FXML
	void abriPdv(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaPDV();
	}

	@FXML
	void ControleDeFuncionarios(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaFuncionario();
	}

	@FXML
	void controleDeClientes(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaCliente();
	}

	@FXML
	void controleDeFornecedores(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaFornecedor();
	}

	@FXML
	void botaoSair(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaLogin();
	}

	@FXML
	void consultaDeProdutos(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaProduto();
	}

	@FXML
	void historicoDeVendas(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaHistoricoDeVendas();
	}

	@FXML
	void posicaoDoDia(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaPosicaoDoDia();
	}

}
