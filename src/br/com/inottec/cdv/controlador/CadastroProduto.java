package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.mascaraText.TextFieldFormatter;
import br.com.inottec.cdv.modelo.Fornecedores;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class CadastroProduto implements Initializable {

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoDescricao;

	@FXML
	private TextField campoDescricaoConsulta;

	@FXML
	private TextField campoPreco;

	@FXML
	private TextField campoQtdEstoque;

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

	@FXML
	private ComboBox<Fornecedores> comboBoxFornecedor;

	@FXML
	private TableView<Produtos> tabelaProdutos;

	List<Fornecedores> listaFornecedores;

	@FXML
	private TabPane tabelaPane;

	// criando um logger
	private static Logger logger = Logger.getLogger(CadastroProduto.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa
		campoCodigo.setEditable(false);
		obterFornecedor();
	}

	//metodo que edita o produto selecionado 
	@FXML
	private void botaoEditar(ActionEvent event) {
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
			
			listaProdutos();
			
			limpacampo();

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

	//metodo que excluir o produto selecionado 
	@FXML
	private void botaoExcluir(ActionEvent event) {

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

			dao.abrirTransacao();

			// removendo Cliente
			dao.remover(produtos);//

			dao.fecharTransacao();

			logger.info("Produto removido com sucesso");

			dao.fechar();
			
			limpacampo();
			
			listaProdutos();
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
	
    //metodo de lista produtos 
	@FXML
	private void botaoLista(ActionEvent event) {
		listaProdutos();
	}

	//metodo que chama a tela de menu 
	@FXML
	private void botaoMenu(ActionEvent event) {

		Main.trocaTela("menuPrincipal");
	}

	//metodo que limpa os campos 
	@FXML
	private void botaoNovo(ActionEvent event) {

		campoCodigo.setText("");
		campoDescricao.setText("");
		campoPreco.setText("");
		campoQtdEstoque.setText("");
	}

	//metodo que salva produto 
	@FXML
	private void botaoSalvar(ActionEvent event) {
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

				limpacampo();

				listaProdutos();

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

	//metodo que pesquisar produto por filtro 
	@FXML
	private void campoDescricaoPesquisar(KeyEvent event) {

		// chamando a lista de clientes com filtro like
		listaClientesComLike();
	}

	//metodo que carrega seleção no dados do produtos 
	@FXML
	private void carregarSelecao(ActionEvent event) {

		// chamndo a tab do index 0 da TabPane que é a area do dados pessoas
		tabelaPane.getSelectionModel().select(0);
		logger.info("Chamando a Tab de index (0)");
		// pegando todos os campos da tabela view da linha celecionada e passando pra a
		// tela de tab dados pessoas
		campoCodigo.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo().toString());
		campoDescricao.setText("teste");
		campoPreco.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getPreco().toString());
		campoQtdEstoque.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getQtdEstoque().toString());
		Fornecedores fornecedor = tabelaProdutos.getSelectionModel().getSelectedItem().getFornecedor();
		/*
		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
		 */

		alteraFornecedor(fornecedor);

		listaProdutos();

		logger.info("Produto adicionado com sucesso na tab dados produto");

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
		List<Produtos> listaProdutos = dao.obterTodosComLike("descricao", "%" + campoDescricaoConsulta.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observaProdutos = FXCollections.observableArrayList(listaProdutos);
		// adicionado o ObservableList na tabela
		tabelaProdutos.setItems(observaProdutos);
		logger.info("Lista de produto com filtro adicionada a tableview");
	}

	// metodo limpa campos
	private void limpacampo() {

		campoCodigo.setText("");
		campoDescricao.setText("");
		campoPreco.setText("");
		campoQtdEstoque.setText("");
	}

	// metodo que cria o comboBox
	private void obterFornecedor() {

		DAO<Fornecedores> dao = new DAO<>(Fornecedores.class);

		listaFornecedores = dao.obterTodos();

		for (Fornecedores s : listaFornecedores) {

			comboBoxFornecedor.getItems().add(s);
		}

		comboBoxFornecedor.getSelectionModel().select(0);

		logger.info("Lista de Fornecedor adicionada a o comboBoxFornecedor");

	}

	// metodo que troca o index do combobox
	private void alteraFornecedor(Fornecedores fornecedor) {

		int i = 0;
		for (Fornecedores s : listaFornecedores) {

			if (!s.getCodigo().equals(fornecedor.getCodigo())) {

				i++;

			} else {

				comboBoxFornecedor.getSelectionModel().select(i);
			}
		}

		logger.info("Fornecedor adicionada a o comboBoxFornecedor");

	}

	//metodo que cria uma mascara do campo preco
	@FXML
	private void formataCampoPreco(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("####");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoPreco);
		// formatando o campo
		tff.formatter();
	}

	//metodo que cria uma mascara do campo estoque
	@FXML
	private void formataCampoQtdEstoque(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("##");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoQtdEstoque);
		// formatando o campo
		tff.formatter();

	}
}