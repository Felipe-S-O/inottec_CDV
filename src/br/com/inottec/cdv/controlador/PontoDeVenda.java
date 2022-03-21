package br.com.inottec.cdv.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class PontoDeVenda {

	@FXML
	private TextField campoBuscarProduto;

	@FXML
	private TextField campoQuantidade;

	@FXML
	private TextField campoValorTotal;

	@FXML
	private TableColumn<?, ?> colunaCodigo;

	@FXML
	private TableColumn<?, ?> colunaPreco;

	@FXML
	private TableColumn<?, ?> colunaProduto;

	@FXML
	private TableColumn<?, ?> colunaQtd;

	@FXML
	private TableColumn<?, ?> colunaSubtotal;

	@FXML
	private Label textoData;

	@FXML
	void botaoPagamento(ActionEvent event) {

	}

	@FXML
	void buscarProduto(KeyEvent event) {

	}

}