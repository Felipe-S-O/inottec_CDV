package br.com.inottec.cdv.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class PontoDeVenda {

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoProduto;

	@FXML
	public TextField campoQuantidade;

	@FXML
	private TextField campoValorTotal;

	@FXML
	private Label textCPF;

	@FXML
	private Label textCliente;

	@FXML
	private Label textOperador;

	@FXML
	private Label textoData;

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

	Double totalApaga = (double) 0;

	static List<Produtos> produtos = new ArrayList<>();

	// criando um logger
	private Logger logger = Logger.getLogger(PontoDeVenda.class);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	@FXML
	void botaoIdentificar(ActionEvent event) {
		Main.trocaTela("telaIdentificao");

	}

	@FXML
	void botaoMenu(ActionEvent event) {
		Main.trocaTela("menuPrincipal");
	}

	@FXML

	void botaoPagamento(ActionEvent event) {

		Main.trocaTela("telaPagamento");

	}

	@FXML
	void botaoPesquisar(ActionEvent event) {
		Main.trocaTela("telaPesquisarProduto");

//		campoBuscarProduto.setText("1");
	}

	@FXML
	void burcar(KeyEvent event) {
		// condição que faz a adiciona produto preciona enter
		if (event.getCode() == KeyCode.ENTER) {

			adicionarProduto();

			campoProduto.setText("");
			campoCodigo.setText("");
			campoCodigo.requestFocus();

		}
	}

	@FXML
	void buscarProduto(KeyEvent event) {

		// condição que faz a adiciona produto preciona enter
		if (event.getCode() == KeyCode.ENTER) {

			apresentaProduto();

			campoQuantidade.requestFocus();

		} else if (event.getCode() == KeyCode.P) {

			Main.trocaTela("telaPesquisarProduto");
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
				
				produtos.add(produto);
				
				adicionarCarrinho();

				somatotal();	
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

	public void adicionarCarrinho() {

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
		ObservableList<Produtos> observaCarrinho = FXCollections.observableArrayList(produtos);
		// adicionado o ObservableList na tabela
		tabelaCarrinho.setItems(observaCarrinho);
		logger.info("Produto adicionado no carinho");
	}

	public void somatotal() {
		
		Double total = (double) 0;
		
		for (Produtos pro : produtos) {

			total = total + pro.getSubtotal();
		}

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
						
						produtos.remove(verifica);
						
						somatotal();

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

	public void teste() {
		int teste = 1 + 1;

		System.out.println(teste);
	}

//	// metodo adiciona codigo do produto
//	public void adicionarProDescricao(double sbt, Produtos pro) {
//
//		produtos.add(pro);
//		adicionarCarrinho();
//	}

}
