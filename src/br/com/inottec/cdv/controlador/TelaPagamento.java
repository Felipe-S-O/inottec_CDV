package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.ItemPedido;
import br.com.inottec.cdv.modelo.Pedido;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	private Button botaoPagamento;

	@FXML
	private TextField campoDesconto;

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

	Double pix, dinheiro, cartao, troco, faltaPaga, pago, total;

	// criando um logger
	private Logger logger = Logger.getLogger(TelaPagamento.class);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa

		totalDaConta.setText(PontoDeVenda.getValor().toString().replace(".", ","));
		campoFaltaPaga.setText(PontoDeVenda.getValor().toString().replace(".", ","));
		valorPago.setText("0,0");
		campoDinheiro.requestFocus();
		campoFaltaPaga.setEditable(false);
		campoTroco.setEditable(false);
	}

	@FXML
	void keyDesconto(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			calculor();
			
			botaoPagamento.requestFocus();			
		}
	}

	@FXML
	void keyCartao(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			calculor();

			campoPix.requestFocus();
		}
	}

	@FXML
	void keyDinheriro(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			calculor();

			campoCartao.requestFocus();
		}
	}

	@FXML
	void keyPIX(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			calculor();

			campoDesconto.requestFocus();
		}
	}

	@FXML
	void botaoVoltar(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaPDV();
	}

	@FXML
	void botaoFinalizarPagamento(ActionEvent event) throws IOException {

		try {

			double faltaPag = faltaPaga;

			Date data = new Date();

			DAO<Pedido> dao = new DAO<Pedido>();

			DAO<ItemPedido> daoItem = new DAO<ItemPedido>();

			if (faltaPag <= 0) {

				Pedido pedido = new Pedido(this.pix, this.dinheiro, this.cartao, this.troco, this.pago, data,
						TelaLogin.operador, IdentificaCliente.getCPFadd(), IdentificaCliente.getNomeAdd());

				dao.incluirAtomico(pedido);

				for (Produtos itens : PontoDeVenda.produtos) {

					ItemPedido itemPedido = new ItemPedido(itens.getCodigo(), itens.getDescricao(), itens.getPreco(),
							itens.getQtdEstoque(), itens.getSubtotal(), pedido);

					System.out.println(itemPedido);

					daoItem.incluirAtomico(itemPedido);
				}

				atualizarEstoque();

				PontoDeVenda.setFinalizarPagamento(true);

				Main tela = new Main();

				tela.criaTelaPDV();

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertInf.setTitle("Finalizar");
				// criando cabeçario do alerta
				alertInf.setHeaderText("Pagamento finalizado com sucesso !");
				// chamando o alerta
				alertInf.show();

			} else {

				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("Fanltando valor a paga !");
				// chamando o alerta
				alertErro.show();

				logger.info("Fanltando valor a paga");
			}

		} catch (Exception e) {

			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("erro no pagamento !");
			// chamando o alerta
			alertErro.show();

			logger.info("Fanltando valor a paga");
		}

	}

	// metodo que para atualizar estoque
	@FXML
	private void atualizarEstoque() {

		try {

			// criando dao do tipo produto
			DAO<Produtos> dao = new DAO<>(Produtos.class);

			for (Produtos itens : PontoDeVenda.produtos) {

				Produtos produto = new Produtos();

				// fazendo uma segunad consulta pq so essa que consegi fazer a alteração
				produto = dao.obterPorID(itens.getCodigo());
				logger.info("Consulta por  ID efetuada com sucesso");
				// abrindo a conexao com o banco pq sem ele não da para fazer a alteração
				dao.abrirTransacao();

				int qtdestoque = produto.getQtdEstoque() - itens.getQtdEstoque();

				produto.setQtdEstoque(qtdestoque);

				// fechando a conexao
				dao.fecharTransacao();
			}

			dao.fechar();

			// finalizando alteração
			logger.info("Estoque atualizado com sucesso!");

		} catch (Exception e) {

			// finalizando alteração
			logger.info("Erro na atualização do estoque!");
		}
	}

	private void calculor() {

		try {

			double pix, dinheiro, cartao, desconto, troco1, faltaPag;

			cartao = Double.parseDouble(campoCartao.getText().replace(",", "."));
			dinheiro = Double.parseDouble(campoDinheiro.getText().replace(",", "."));
			pix = Double.parseDouble(campoPix.getText().replace(",", "."));
			desconto = Double.parseDouble(campoDesconto.getText().replace(",", "."));

			if (cartao >= 0 && dinheiro >= 0 && pix >= 0 && desconto >= 0) {

				pago = cartao + pix + dinheiro;

				valorPago.setText(pago.toString().replace(".", ","));

				total = Double.parseDouble(totalDaConta.getText().replace(",", "."));

				total = total - desconto;

				faltaPag = total - pago;

				troco1 = pago - total;

				troco = troco1;

				faltaPaga = faltaPag;

				if (faltaPag > 0) {

					String valorFormatado = String.format("%.2f", faltaPag);

					campoFaltaPaga.setText(valorFormatado.replace(".", ","));

				} else {
					campoFaltaPaga.setText("0,0");

					this.pix = pix;
					this.dinheiro = dinheiro;
					this.cartao = cartao;

				}

				if (troco1 > 0) {

					String valorFormatado = String.format("%.2f", troco);

					campoTroco.setText(valorFormatado.replace(".", ","));

				} else {
					campoTroco.setText("0,0");

				}
			} else {
				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("valor digitado incorretamente !");
				// chamando o alerta
				alertErro.show();
			}

		} catch (Exception e) {

			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("valor digitado incorretamente");
			// chamando o alerta
			alertErro.show();

		}

	}

}
