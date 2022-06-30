package br.com.inottec.cdv.controlador;

import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


public class PesquisarProduto {


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

	public static String codigo, descricao, controlePesquisarProduto;
	
	// criando um logger
	private static Logger logger = Logger.getLogger(PesquisarProduto.class);
	

	public static String getCodigo() {
		return codigo;
	}

	public static void setCodigo(String codigo) {
		PesquisarProduto.codigo = codigo;
	}

	public static String getDescricao() {
		return descricao;
	}

	public static void setDescricao(String descricao) {
		PesquisarProduto.descricao = descricao;
	}

	@FXML
	void botaoAdicionar(ActionEvent event) throws IOException {
		
		setCodigo(tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo().toString());
		setDescricao(tabelaProdutos.getSelectionModel().getSelectedItem().getDescricao().toString());
		
		controlePesquisarProduto = "botaoAdicionar";
		
		Main tela = new Main();

		tela.criaTelaPDV();

	}

	@FXML
	void botaoVoltar(ActionEvent event) throws IOException {
		
		Main tela = new Main();

		tela.criaTelaPDV();

	}

	@FXML
	void botaoPesquisar(ActionEvent event) {

		listaProdutos();

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