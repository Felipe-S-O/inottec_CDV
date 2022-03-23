package br.com.inottec.cdv.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TelaPagamento {

    @FXML
    private Text TotalDaConta;

    @FXML
    private Text ValorPago;

    @FXML
    private TextField campoCartao;

    @FXML
    private TextField campoDinheiro;

    @FXML
    private TextField campoFaltaPaga;

    @FXML
    private TextField campoPix;

    @FXML
    private TextField campoTroco;

    @FXML
    void botaoFinalizarPagamento(ActionEvent event) {

    }

}
