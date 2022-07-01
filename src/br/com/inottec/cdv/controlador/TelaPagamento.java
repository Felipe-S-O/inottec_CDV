package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.mascaraText.TextFieldFormatter;
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

	Double troco, faltaPaga, pago, total;

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
		
//		TextFieldFormatter tff = new TextFieldFormatter();
//		// crinado a mascara
//		tff.setMask("###,##");
//		// os numeros que pode ser colocado
//		tff.setCaracteresValidos("0123456789");
//		// o campo que vai receber a formatação
//		tff.setTf(campoCartao);
//		// formatando o campo
//		tff.formatter();

		if (event.getCode() == KeyCode.ENTER) {

			double pix, dinheiro, cartao, troco1, faltaPag;

			cartao = Double.parseDouble(campoCartao.getText().replace(",", "."));
			dinheiro = Double.parseDouble(campoDinheiro.getText().replace(",", "."));
			pix = Double.parseDouble(campoPix.getText().replace(",", "."));

			/*
			 * campo cartão
			 */

			if (cartao > 0 && dinheiro <= 0 && pix <= 0) {

				valorPago.setText(campoCartao.getText().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - cartao;

				troco1 = cartao - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
				/*
				 * campo dinheiro e cartão
				 */
			} else if (cartao > 0 && dinheiro > 0 && pix <= 0) {

				pago = cartao + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo cartão pix
				 */
			} else if (cartao > 0 && dinheiro <= 0 && pix > 0) {

				pago = cartao + pix;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo cartão dinheiro pix
				 */

			} else if (cartao > 0 && dinheiro > 0 && pix > 0) {

				pago = cartao + pix + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
		
//		TextFieldFormatter tff = new TextFieldFormatter();
//		// crinado a mascara
//		tff.setMask("###,##");
//		// os numeros que pode ser colocado
//		tff.setCaracteresValidos("0123456789");
//		// o campo que vai receber a formatação
//		tff.setTf(campoDinheiro);
//		// formatando o campo
//		tff.formatter();
		
		if (event.getCode() == KeyCode.ENTER) {

			double pix, dinheiro, cartao, troco1, faltaPag;

			cartao = Double.parseDouble(campoCartao.getText().replace(",", "."));
			dinheiro = Double.parseDouble(campoDinheiro.getText().replace(",", "."));
			pix = Double.parseDouble(campoPix.getText().replace(",", "."));

			/*
			 * campo dinheiro
			 */

			if (cartao <= 0 && dinheiro > 0 && pix <= 0) {

				valorPago.setText(campoDinheiro.getText().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - dinheiro;

				troco1 = dinheiro - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
				/*
				 * campo dinheiro e cartão
				 */
			} else if (cartao > 0 && dinheiro > 0 && pix <= 0) {

				pago = cartao + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo dinheiro pix
				 */
			} else if (cartao <= 0 && dinheiro > 0 && pix > 0) {

				pago = dinheiro + pix;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo cartão dinheiro pix
				 */

			} else if (cartao > 0 && dinheiro > 0 && pix > 0) {

				pago = cartao + pix + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
	void keyPIX(KeyEvent event) {
		
//		TextFieldFormatter tff = new TextFieldFormatter();
//		// crinado a mascara
//		tff.setMask("###,##");
//		// os numeros que pode ser colocado
//		tff.setCaracteresValidos("0123456789");
//		// o campo que vai receber a formatação
//		tff.setTf(campoPix);
//		// formatando o campo
//		tff.formatter();
//		
		if (event.getCode() == KeyCode.ENTER) {

			double pix, dinheiro, cartao, troco1, faltaPag;

			cartao = Double.parseDouble(campoCartao.getText().replace(",", "."));
			dinheiro = Double.parseDouble(campoDinheiro.getText().replace(",", "."));
			pix = Double.parseDouble(campoPix.getText().replace(",", "."));

			/*
			 * campo pix
			 */

			if (cartao <= 0 && dinheiro <= 0 && pix > 0) {

				valorPago.setText(campoPix.getText().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pix;

				troco1 = pix - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
				/*
				 * campo dinheiro e pix
				 */
			} else if (cartao <= 0 && dinheiro > 0 && pix > 0) {

				pago = pix + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo cartão pix
				 */
			} else if (cartao > 0 && dinheiro <= 0 && pix > 0) {

				pago = cartao + pix;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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

				/*
				 * campo cartão dinheiro pix
				 */

			} else if (cartao > 0 && dinheiro > 0 && pix > 0) {

				pago = cartao + pix + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

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
	void botaoVoltar(ActionEvent event) throws IOException {
		Main tela = new Main();

		tela.criaTelaPDV();
	}

	@FXML
	void botaoFinalizarPagamento(ActionEvent event) throws IOException {
		
		double faltaPag = faltaPaga;
		
		if(faltaPag == 0) {
			
			Main tela = new Main();
			
			tela.criaTelaPDV();
		}else {
			
		}
		

	}

}
