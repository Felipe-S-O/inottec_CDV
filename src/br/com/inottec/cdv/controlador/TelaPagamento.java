package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.util.ResourceBundle;
import br.com.inottec.cdv.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class TelaPagamento implements Initializable {

	@FXML
	private Text totalDaConta;

	@FXML
	private Text valorPago;

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

	Double cartao, dinheiro, pix, troco, faltaPaga, pago, total;

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa

		totalDaConta.setText(PontoDeVenda.getValor().toString().replace(".", ","));
		campoFaltaPaga.setText(PontoDeVenda.getValor().toString().replace(".", ","));
		valorPago.setText("0,0");
		campoFaltaPaga.setEditable(false);
		campoTroco.setEditable(false);
	}

	@FXML
	void keyCartao(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			double pix, dinheiro, cartao, troco1, faltaPag;
			
			 cartao = Double.parseDouble(campoCartao.getText().replace(",", "."));
			 dinheiro= Double.parseDouble(campoDinheiro.getText().replace(",", "."));
			 pix = Double.parseDouble(campoPix.getText().replace(",", "."));

			if (cartao > 0 || dinheiro <= 0 || pix <= 0 ) {


				valorPago.setText(campoCartao.getText().replace(".", ","));
				
				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - cartao;
				
				troco1 = cartao - total;
				
				troco = troco1;
				
				faltaPaga =  faltaPag;			
				
				
				if (faltaPag > 0) {

					String valorFormatado = String.format("%.2f", faltaPag);
					
					campoFaltaPaga.setText(valorFormatado.replace(".", ","));

				} else {
					campoFaltaPaga.setText("0,0");

				}
				
				if (troco1 > 0) {
					
					String valorFormatado = String.format("%.2f", troco);
					
					campoTroco.setText(valorFormatado.replace(".", ","));

				} else {
					campoTroco.setText("0,0");

				}
			}

		}

	}

	@FXML
	void keyDinheriro(KeyEvent event) {

	}

	@FXML
	void keyPIX(KeyEvent event) {

	}

	@FXML
	void botaoVoltar(ActionEvent event) throws IOException {
		Main tela = new Main();

		tela.criaTelaPDV();
	}

	@FXML
	void botaoFinalizarPagamento(ActionEvent event) {

	}

}
