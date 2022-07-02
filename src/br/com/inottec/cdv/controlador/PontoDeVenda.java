package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PontoDeVenda implements Initializable {

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoProduto;

	@FXML
	private TextField campoQuantidade;

	@FXML
	private TextField campoValorTotal;

	@FXML
	private Label textCPF;

	@FXML
	private Label textCliente;

	@FXML
	private Label textOperador;

	@FXML
	private Label textData;

	@FXML
	private TableColumn<Produtos, Long> colunaCodigo;

	@FXML
	private TableColumn<Produtos, Button> colunaExcluir;

	@FXML
	private TableColumn<Produtos, Double> colunaPreco;

	@FXML
	private TableColumn<Produtos, String> colunaProduto;

	@FXML
	private TableColumn<Produtos, Integer> colunaQtd;

	@FXML
	private TableColumn<Produtos, Double> colunaSubtotal;

	@FXML
	private TableView<Produtos> tabelaCarrinho;

	public static Double valor;

	public static boolean  validarPro = false, finalizarPagamento = false;

//=========================================== get e set =================================================


	public static boolean isValidarPro() {
		return validarPro;
	}

	public static void setValidarPro(boolean validarPro) {
		PontoDeVenda.validarPro = validarPro;
	}

	public static Double getValor() {
		return valor;
	}

	public static void setValor(Double valor) {
		PontoDeVenda.valor = valor;
	}

	public static boolean isFinalizarPagamento() {
		return finalizarPagamento;
	}

	public static void setFinalizarPagamento(boolean finalizarPagamento) {
		PontoDeVenda.finalizarPagamento = finalizarPagamento;
	}

//============================================//========================================================
	public static List<Produtos> produtos = new ArrayList<>();

	public List<Produtos> listaDeProdutos = new ArrayList<>();

	// criando um logger
	private Logger logger = Logger.getLogger(PontoDeVenda.class);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox

	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa

		textOperador.setText(TelaLogin.operador);
		finalizarCarrinho();
		iniciarCarrinho();
		somatotal();
		dataAtual();
		pesquisarProduto();
		identificaCliente();

	}

	private void dataAtual() {
		DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		textData.setText(dtf4.format(LocalDateTime.now()));
	}

	private void pesquisarProduto() {

		if ("botaoAdicionar".equals(PesquisarProduto.getControlePesquisarProduto())) {

			campoCodigo.setText(PesquisarProduto.getCodigo());

			campoProduto.setText(PesquisarProduto.getDescricao());
		}
	}

	private void identificaCliente() {

		if ("botaoIdentificar".equals(IdentificaCliente.getControleID())) {

			textCPF.setText(IdentificaCliente.getCPFadd());

			textCliente.setText(IdentificaCliente.getNomeAdd());

		}
	}

	@FXML
	void botaoIdentificar(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaIdentificar();

	}

	@FXML
	void botaoMenu(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaMenu();
	}

	@FXML

	void botaoPagamento(ActionEvent event) throws IOException {

		if (!campoValorTotal.getText().equals("") || !listaDeProdutos.isEmpty()) {

			Main tela = new Main();

			tela.criaTelaPagamento();

		} else {
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Adicione itens no carrinho!");
			// chamando o alerta
			alertErro.show();
		}

	}

	@FXML
	void botaoPesquisarProduto(ActionEvent event) throws IOException {

		Main tela = new Main();

		tela.criaTelaPesquisarProduto();
	}

	@FXML
	void campoProduto(KeyEvent event) throws IOException {

		if (event.getCode() == KeyCode.P) {

			Main tela = new Main();

			tela.criaTelaPesquisarProduto();
		}

	}

	@FXML
	void campoQtdDoProduto(KeyEvent event) {
		// condição que faz a adiciona produto preciona enter
		if (event.getCode() == KeyCode.ENTER) {

			adicionarProduto();

			campoProduto.setText("");
			campoCodigo.setText("");

			campoCodigo.requestFocus();
			
			adicionaCarrinhoStatic();

			PesquisarProduto.setControlePesquisarProduto("vazio");

			IdentificaCliente.setControleID("vazio");

		}
	}

	@FXML
	void campoCodigoDoProduto(KeyEvent event) {

		// condição que faz a adiciona produto preciona enter
		if (event.getCode() == KeyCode.ENTER) {

			apresentaProduto();

			campoQuantidade.requestFocus();
		}

	}

	private void apresentaProduto() {
		try {

			if (!campoCodigo.getText().equals("") || !campoCodigo.getText().equals(null)) {

				Long idProduto = Long.parseLong(campoCodigo.getText());

				Produtos produto;

				DAO<Produtos> dao = new DAO<>(Produtos.class);

				produto = dao.obterPorID(idProduto);

				campoProduto.setText(produto.getDescricao());

			}
		} catch (Exception e) {

			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Codigo do produto !");
			// chamando o alerta
			alertErro.show();
		}

	}

	private void adicionarProduto() {
		try {

			if (!campoCodigo.getText().equals("") || !campoCodigo.getText().equals(null)) {

				Long idProduto = Long.parseLong(campoCodigo.getText());

				Produtos produto;

				DAO<Produtos> dao = new DAO<>(Produtos.class);

				produto = dao.obterPorID(idProduto);

				produto.setQtdEstoque(Integer.parseInt(campoQuantidade.getText()));

				double subtotal = produto.getQtdEstoque() * produto.getPreco();

				produto.setExcluir(botaoexcluir());

				produto.setSubtotal(subtotal);

				listaDeProdutos.add(produto);

				adicionarCarrinho();

				somatotal();

				Double valor = Double.parseDouble(campoValorTotal.getText());

				setValor(valor);

//				limparCarrinhoStatic();
				
			}
		} catch (Exception e) {

			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Codigo do produto !");
			// chamando o alerta
			alertErro.show();
		}

	}

	private void adicionarCarrinho() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaQtd.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("qtdEstoque"));

		colunaSubtotal.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("subtotal"));

		colunaExcluir.setCellValueFactory(new PropertyValueFactory<Produtos, Button>("excluir"));

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observCarrinho = FXCollections.observableArrayList(listaDeProdutos);
		// adicionado o ObservableList na tabela
		tabelaCarrinho.setItems(observCarrinho);

	}

	/// metodo que inicia o carrinho quando troca de tela
	private void iniciarCarrinho() {

		if (validarPro) {

			for (Produtos pdv : produtos) {

				Produtos lista = new Produtos(pdv.getCodigo(), pdv.getDescricao(), pdv.getSubtotal(),
						pdv.getQtdEstoque(), pdv.getFornecedor(), pdv.getPreco());

				lista.setExcluir(botaoexcluir());

				listaDeProdutos.add(lista);
			}
			
			adicionarCarrinho();

		}

	}

// metodo que adiciona carrinho na lista static de produto
	private void adicionaCarrinhoStatic() {

		limparCarrinhoStatic();
		
		if(!listaDeProdutos.isEmpty()) {			
			
			for (Produtos pdv : listaDeProdutos) {
				
				Produtos lista = new Produtos(pdv.getCodigo(), pdv.getDescricao(), pdv.getSubtotal(), pdv.getQtdEstoque(),
						pdv.getFornecedor(), pdv.getPreco());
				
				produtos.add(lista);
				
				
				/// o erro esta aqui tenta coloca no botão de voltar
				
				validarPro = true;		
				
			}
		}
	}

	// metodo que limpar carrinho na static de produtos
	private void limparCarrinhoStatic() {

		int i = 0;

		if (!produtos.isEmpty()) {

			for (Produtos pdv : produtos) {

				logger.info("Produto removido do carrinho static " + pdv.getDescricao());
				produtos.remove(i);

				i++;
			}
		}
	}

	private void somatotal() {

		Double total = (double) 0;

		for (Produtos pro : listaDeProdutos) {

			total = total + pro.getSubtotal();
		}

		setValor(total);
		campoValorTotal.setText(total.toString());
	}

	// metodo que adiciona botão de Excluir no carrinho
	private Button botaoexcluir() {

		Button excluir = new Button("Excluir");

		excluir.setOnAction(Event -> {

			if (confirmarExclusao()) {

				for (Produtos verifica : tabelaCarrinho.getItems()) {
					if (verifica.getExcluir() == excluir) {

						tabelaCarrinho.getSelectionModel().clearSelection();

						tabelaCarrinho.getItems().remove(verifica);

						listaDeProdutos.remove(verifica);

						adicionarCarrinho();

						somatotal();

						limparCarrinhoStatic();

						logger.info("Produto removido do carinho com sucesso");

					}
				}
			}

		});

		return excluir;
	}

//	Metodo que gera mensagen de confirmação de para exlcur item do carrinho 
	private boolean confirmarExclusao() {

		boolean excluir = false;

		Alert alert = new Alert(AlertType.WARNING);
		// criando titulo do alerta
		alert.setTitle("Excluir");
		// criando cabeçario do alerta
		alert.setHeaderText("Tem certeza que deseja excluir esse item!");

		// Criando um butão cancelar
		ButtonType cancelar = new ButtonType("CANCELAR", ButtonData.CANCEL_CLOSE);
		alert.getDialogPane().getButtonTypes().add(cancelar);

//		criando condição 
		Optional<ButtonType> resultado = alert.showAndWait();
		if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
			excluir = true;
		}

		return excluir;

	}

	// quando finalizar o pagamento vai limpa o carrinho
	private void finalizarCarrinho() {

		if (finalizarPagamento) {

			for (int i = 0; i < produtos.size(); i++) {

				produtos.remove(i);

				tabelaCarrinho.getItems().remove(i);

				textCPF.setText("##.###.###-##");

				textCliente.setText("Não identificado");

			}

			finalizarPagamento = false;
		}

	}

}
