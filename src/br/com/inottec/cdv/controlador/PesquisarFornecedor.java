package br.com.inottec.cdv.controlador;

import java.util.List;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Fornecedores;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class PesquisarFornecedor {

	@FXML
	private TextField campoPesquisar;

	@FXML
	private TableColumn<Fornecedores, String> colunaCNPJ;

	@FXML
	private TableColumn<Fornecedores, Long> colunaCodigo;

	@FXML
	private TableColumn<Fornecedores, String> colunaEmail;

	@FXML
	private TableColumn<Fornecedores, String> colunaEstado;

	@FXML
	private TableColumn<Fornecedores, String> colunaNome;

	@FXML
	private TableColumn<Fornecedores, String> colunaTelefone;

	@FXML
	private TableView<Fornecedores> tabelaFornecedor;

	@FXML
	void botaoAdicionar(ActionEvent event) {

//		String nome, cnpj, email, estado, telefone;
//		Long codigo;
//
//		codigo = tabelaFornecedor.getSelectionModel().getSelectedItem().getCodigo();
//		nome = tabelaFornecedor.getSelectionModel().getSelectedItem().getNome().toString();
//		cnpj = tabelaFornecedor.getSelectionModel().getSelectedItem().getCnpj().toString();
//		email = tabelaFornecedor.getSelectionModel().getSelectedItem().getEmail().toString();
//		telefone = tabelaFornecedor.getSelectionModel().getSelectedItem().getTelefone().toString();
//		estado = tabelaFornecedor.getSelectionModel().getSelectedItem().getEndereco().toString();
//
//		Fornecedores fornecedor = new Fornecedores(codigo, nome, cnpj, email, telefone, estado);
//
//		CadastroProduto cadastroProduto = new CadastroProduto();
//
//		//cadastroProduto.adicionaFornecedor(fornecedor);		
//	
//		
//		Main.trocaTela("telaProduto");
		

	}


	@FXML
	void botaoPesquisar(ActionEvent event) {
		listaFornecedores();
	}

	@FXML
	void campoNomePesquisar(KeyEvent event) {
		listaFornecedoresComLike();
	}

	// metodo obter um lista Fornecedores com o like
	public void listaFornecedoresComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Fornecedores, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("nome"));

		colunaCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cnpj"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("telefone"));

		// crinado a Classe dao do tipo Fornecedores
		DAO<Fornecedores> dao = new DAO<Fornecedores>(Fornecedores.class);

		// crinado um lista do tipo Fornecedores com filtor e adicionando um
		// listaFornecedores
		List<Fornecedores> listaFornecedores = dao.obterTodosComLike("nome", "%" + campoPesquisar.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de Fornecedores
		ObservableList<Fornecedores> observaFornecedores = FXCollections.observableArrayList(listaFornecedores);
		// adicionado o ObservableList na tabela
		tabelaFornecedor.setItems(observaFornecedores);
		// logger.info("Lista de clientes com filtro adicionada a tableview");
	}

	// metodo obter um lista Fornecedores
	public void listaFornecedores() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Fornecedores, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("nome"));

		colunaCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cnpj"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("telefone"));

//		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Fornecedores
		DAO<Fornecedores> dao = new DAO<Fornecedores>(Fornecedores.class);

		// crinado um lista do tipo Fornecedor e adicionando um lista de Fornecedores
		List<Fornecedores> listaFornecedores = dao.obterTodos();

		// crinado um lista do tipo ObservableList que recebe uma lista de Fornecedores
		ObservableList<Fornecedores> observaFornecedores = FXCollections.observableArrayList(listaFornecedores);
		// adicionado o ObservableList na tabela
		tabelaFornecedor.setItems(observaFornecedores);
		// logger.info("Lista de clientes adicionada a tableview");
	}

}
