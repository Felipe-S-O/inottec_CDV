package br.com.inottec.cdv.controlador;

import br.com.inottec.cdv.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuPrincipal {

	@FXML
	void abriPdv(ActionEvent event) {

		Main.trocaTela("telaPDV");
	}

	@FXML
	void ControleDeFuncionarios(ActionEvent event) {

		Main.trocaTela("telaFuncionario");
	}

	@FXML
	void controleDeClientes(ActionEvent event) {
		Main.trocaTela("telaClientes");
	}

	@FXML
	void controleDeFornecedores(ActionEvent event) {
		Main.trocaTela("telaFornecedor");
	}

	@FXML
	void controleEstoque(ActionEvent event) {
		Main.trocaTela("telaProduto");

	}

	@FXML
	void historicoDeVendas(ActionEvent event) {
		Main.trocaTela("telaHistoricoDeVendas");
	}

	@FXML
	void posicaoDoDia(ActionEvent event) {
		Main.trocaTela("telaPosicaoDoDia");
	}

	@FXML
	void trocarDeUsuario(ActionEvent event) {
		Main.trocaTela("telaLogin");
	}

}
