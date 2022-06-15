package br.com.inottec.cdv.controlador;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Fornecedores;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PesquisarProduto {

	private Long codigo;

	private double totalApaga;

	@FXML
	private TextField campoDescrição;

	@FXML
	private TextField campoPesquisar;

	@FXML
	private TextField campoQuantidade;

	@FXML
	private TableColumn<Produtos, Long> colunaCodigo;

	@FXML
	private TableColumn<Produtos, String> colunaDescricao;

	@FXML
	private TableColumn<Produtos, String> colunaFornecedor;

	@FXML
	private TableColumn<Produtos, Double> colunaPreco;

	@FXML
	private TableColumn<Produtos, Integer> colunaQtdEstoque;

	@FXML
	private TableView<Produtos> tabelaProdutos;

	// criando um logger
	private static Logger logger = Logger.getLogger(PesquisarProduto.class);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	PontoDeVenda pdv = new PontoDeVenda();

	@FXML
	void botaoAdicionar(ActionEvent event) {
		try {

			if (!campoDescrição.equals("") || !campoDescrição.equals(null)) {

				Produtos produto;

				DAO<Produtos> dao = new DAO<>(Produtos.class);

				produto = dao.obterPorID(codigo);

				produto.setQtdEstoque(Integer.parseInt(campoQuantidade.getText()));

				double subtotal = produto.getQtdEstoque() * produto.getPreco();

				produto.setSubtotal(subtotal);

				pdv.adicionarProDescricao(subtotal, produto);

				Main.trocaTela("telaPDV");
				
				logger.info("Produto adicionado com sucesso na tab dados produto");

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

	@FXML
	void botaoVoltar(ActionEvent event) {

		Main.trocaTela("telaPDV");
	}

	@FXML
	void botaoPesquisar(ActionEvent event) {

		listaProdutos();

	}

	@FXML
	void carregaselecao(MouseEvent event) {

		codigo = tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo();
		campoDescrição.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getDescricao());
	}

	@FXML
	void campoPesquisarProduto(KeyEvent event) {

		listaClientesComLike();

	}
	
	// metodo obter um lista clientes
	private void listaProdutos() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("qtdEstoque"));

		colunaFornecedor.setCellValueFactory(new PropertyValueFactory<Produtos, String>("fornecedor"));

		logger.info("Todos os Atributo de produto forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Produtos> dao = new DAO<Produtos>(Produtos.class);

		// crinado um lista do tipo Cliente e adicionando um lista de Clientes
		List<Produtos> listaProdutos = dao.obterTodos();

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observaProdutos = FXCollections.observableArrayList(listaProdutos);
		// adicionado o ObservableList na tabela
		tabelaProdutos.setItems(observaProdutos);
		logger.info("Lista de produto adicionada a tableview");
	}

	// metodo obter um lista clientes com o like
	private void listaClientesComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("qtdEstoque"));

		logger.info("Todos os Atributo de produto forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Produtos> dao = new DAO<Produtos>(Produtos.class);

		// crinado um lista do tipo Cliente com filtor e adicionando um listaclinte
		List<Produtos> listaProdutos = dao.obterTodosComLike("descricao", "%" + campoPesquisar.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observaProdutos = FXCollections.observableArrayList(listaProdutos);
		// adicionado o ObservableList na tabela
		tabelaProdutos.setItems(observaProdutos);
		logger.info("Lista de produto com filtro adicionada a tableview");
	}

}