package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class CadastroProduto {

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoDescricao;

	@FXML
	private TextField campoPreco;

	@FXML
	private TextField campoQtdEstoque;

	@FXML
	private ComboBox<Fornecedores> comboBoxFornecedor;
	@FXML
	private TextField campoNomeConsulta;

	@FXML
	private TableView<Produtos> tabelaProdutos;

	@FXML
	private TableColumn<Produtos, Long> colunaCodigo;

	@FXML
	private TableColumn<Produtos, String> colunaDescricao;

	@FXML
	private TableColumn<Produtos, Double> colunaPreco;

	@FXML
	private TableColumn<Produtos, Integer> colunaQtdEstoque;

	@FXML
	private TableColumn<Produtos, String> colunaFornecedor;

	// criando um logger
	private static Logger logger = Logger.getLogger(CadastroProduto.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);
	
	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa
		obterFornecedor();
		//listaProdutos();

	}

	@FXML
	void botaoEditar(ActionEvent event) {

		try {
			// crinado objeto cliente
			Produtos produtos = new Produtos();
			// pegando a opção selecionada no comboBox e colocando na variavel local uf
			Fornecedores fornecedor = comboBoxFornecedor.getSelectionModel().getSelectedItem();

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			// criando dao do tipo clientes
			DAO<Produtos> dao = new DAO<>(Produtos.class);

			/*
			 * fazendo a consulta por codigo do cliente que é para ajuda a outra consuta que
			 * faz alteração que é essa que ta abaixo
			 */
			produtos = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");
			// fazendo uma segunad consulta pq so essa que consegi fazer a alteração
			produtos = dao.obterPorID(produtos.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");
			// abrindo a conexao com o banco pq sem ele não da para fazer a alteração
			dao.abrirTransacao();

			// fazendo as alterações no dado do cliente não é obrigatorio ta todos os campos
			// preenchido
			produtos.setDescricao(campoDescricao.getText());
			produtos.setPreco(Double.parseDouble(campoPreco.getText()));
			produtos.setQtdEstoque(Integer.parseInt(campoQtdEstoque.getText()));
			produtos.setFornecedor(fornecedor);

			// fechando a conexao
			dao.fecharTransacao();

			dao.fechar();

			// finalizando alteração
			logger.info("Produtos Alterado com sucesso");

			/*
			 * metodo que chama a lista de Clientes para a tab consulta Clientes que é para
			 * quando altera o cliente ja exibi na tela de consulta
			 */
			// listaProdutos();
			logger.info("Consultando lista de Produto com sucesso");
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Produto Alterado com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Produto não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Produto não selecionado!");
			// chamando o alerta
			alertErro.show();

		}

	}

	@FXML
	void botaoExcluir(ActionEvent event) {

		try {

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			Produtos produtos = new Produtos();

			DAO<Produtos> dao = new DAO<>(Produtos.class);

			// fazendo a consulta por id
			produtos = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");

			// fazendo uma consulta que faz a exclusão por id
			produtos = dao.obterPorID(produtos.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");

//		// verificando se cliente é nulo
//		if (cliente != null) {

			// logger.info("Cliente encontrado");

			dao.abrirTransacao();

			// removendo Cliente
			dao.remover(produtos);//

			dao.fecharTransacao();

			logger.info("Produto removido com sucesso");

			dao.fechar();
			/*
			 * metodo que chama a lista de Clientes para a tab consulta Clientes que é para
			 * quando altera o cliente ja exibi na tela de consulta
			 */
			// listaProdutos();
			logger.info("Consultando lista de produto com sucesso");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Produto Excluidor com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Produto não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Produto não selecionado!");
			// chamando o alerta
			alertErro.show();
		}

	}

	@FXML
	void botaoListaTodos(ActionEvent event) {

		listaProdutos();
	}

	@FXML
	void botaoMenu(ActionEvent event) {
       
		Main.trocaTela("menuPrincipal");

	}

	@FXML
	void botaoNovo(ActionEvent event) {

		campoDescricao.setText(null);
		campoPreco.setText(null);
		campoQtdEstoque.setText(null);

	}

	@FXML
	void botaoSalvar(ActionEvent event) {

		// crinado um condição para o nome ser obrigatorio
		if (!campoDescricao.getText().equals("")) {
			try {

				// pegando a opção selecionada no comboBox e colocando na variavel local uf
				Fornecedores fornecedor = comboBoxFornecedor.getSelectionModel().getSelectedItem();

				// criando o dao clientes para adiciona o clientes no banco
				DAO<Produtos> dao = new DAO<>(Produtos.class);

				// adicionando os campos ao cliente
				Produtos produto = new Produtos(campoDescricao.getText(), Double.parseDouble(campoPreco.getText()),
						Integer.parseInt(campoQtdEstoque.getText()), fornecedor);

				// gravando cliente no banco e fechndo conexão
				dao.incluirAtomico(produto).fechar();

				logger.info("Produto Cadastrado com Sucesso!");

				/*
				 * metodo que chama a lista de Clientes para a tab consulta cLiente para quando
				 * adicona um novo cliente ja exibi na tela de consulta
				 */
				// listaProdutos();

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertInf.setTitle("Mensagem");
				// criando cabeçario do alerta
				alertInf.setHeaderText("Cadastrado com Sucesso!");
				// chamando o ale
				alertInf.show();

			} catch (Exception e) {

				logger.info("erro ao salvar produto" + e);
				// criando um alerta de confirmação
				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("Erro ao salvar produto!");
				// chamando o alerta
				alertErro.show();
			}

		} else {
			// finalizando alteração
			logger.info("Campo obrigatorio vazio");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Campo nome é obrigatorio!");
			// chamando o alerta
			alertErro.show();
		}

	}

	// metodo obter um lista clientes
	@FXML
	void campoNomePesquisar(KeyEvent event) {

		// chamando a lista de clientes com filtro like
		listaClientesComLike();
	}

	@FXML
	void carregarSelecao(ActionEvent event) {

		// chamndo a tab do index 0 da TabPane que é a area do dados pessoas

		logger.info("Chamando a Tab de index (0)");
		// pegando todos os campos da tabela view da linha celecionada e passando pra a
		// tela de tab dados pessoas
		campoCodigo.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo().toString());
		campoDescricao.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getDescricao().toString());
		campoPreco.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getPreco().toString());
		campoQtdEstoque.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getQtdEstoque().toString());
		comboBoxFornecedor
				.setPromptText(tabelaProdutos.getSelectionModel().getSelectedItem().getFornecedor().toString());
		/*
		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
		 */
		listaProdutos();
		logger.info("Cliente adicionado com sucesso na tab dados pessoas");

	}

	// metodo que cria o comboBox
	public void obterFornecedor() {

		// crinado a Classe dao do tipo Fornecedores
		DAO<Fornecedores> dao = new DAO<Fornecedores>(Fornecedores.class);

		// crinado um lista do tipo Fornecedores e adicionando um lista de Fornecedores
		List<Fornecedores> listaFornecedores = dao.obterTodos();

		/*
		 * adicionado listaFornecedores no atributo ObeservFornecedores no formato
		 * ObservableList pq o comboBox só aceita um ObservableList
		 */ ObservableList<Fornecedores> ObeservFornecedores = FXCollections.observableArrayList(listaFornecedores);

		// adicionando listaDeItemDeParcels no comboBox
		comboBoxFornecedor.setItems(ObeservFornecedores);

		/*
		 * adicionado um index 0 como padão no combBox para quando executar o metodo
		 * mauseCliqueNaTabelaClientes para não da um erro na coluna Fornecedores porque
		 * ela esta vazia
		 */comboBoxFornecedor.getSelectionModel().select(0);

		logger.info("Lista de Fornecedores adicionada a o comboBox");
	}

	// metodo obter um lista clientes
	public void listaProdutos() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("QtdEstoque"));

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
	public void listaClientesComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaDescricao.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaQtdEstoque.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("QtdEstoque"));

		logger.info("Todos os Atributo de produto forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Produtos> dao = new DAO<Produtos>(Produtos.class);

		// crinado um lista do tipo Cliente com filtor e adicionando um listaclinte
		List<Produtos> listaProdutos = dao.obterTodosComLike("nome", "%" + campoNomeConsulta.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observaProdutos = FXCollections.observableArrayList(listaProdutos);
		// adicionado o ObservableList na tabela
		tabelaProdutos.setItems(observaProdutos);
		logger.info("Lista de produto com filtro adicionada a tableview");
	}
}
