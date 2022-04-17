package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.mascaraText.TextFieldFormatter;
import br.com.inottec.cdv.modelo.CEP;
import br.com.inottec.cdv.modelo.Clientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;

public class CadastroClientes implements Initializable {

	// id dos campos do FXML
	@FXML
	private TabPane tabelaPane;

	@FXML
	private TextField campoCodigo;

	@FXML
	private TextField campoNome;

	@FXML
	private TextField campoEmail;

	@FXML
	private TextField campoCelular;

	@FXML
	private TextField campoTelefone;

	@FXML
	private TextField campoCEP;

	@FXML
	private TextField campoEndereco;

	@FXML
	private TextField campoNumero;

	@FXML
	private TextField campoBairro;

	@FXML
	private TextField campoCidade;

	@FXML
	private TextField campoComplemeto;

	@FXML
	private TextField campoRG;

	@FXML
	private TextField campoCPF;

	@FXML
	private ComboBox<String> comboBoxUF;

	@FXML
	private TextField campoNomeConsulta;

	@FXML
	private TableView<Clientes> tabelaClientes;

	@FXML
	private TableColumn<Clientes, Long> colunaCodigo;

	@FXML
	private TableColumn<Clientes, String> colunaNome;

	@FXML
	private TableColumn<Clientes, String> colunaRG;

	@FXML
	private TableColumn<Clientes, String> colunaCPF;

	@FXML
	private TableColumn<Clientes, String> colunaEmail;

	@FXML
	private TableColumn<Clientes, String> colunaTelefone;

	@FXML
	private TableColumn<Clientes, String> colunaCelular;

	@FXML
	private TableColumn<Clientes, String> colunaCEP;

	@FXML
	private TableColumn<Clientes, String> colunaEndereco;

	@FXML
	private TableColumn<Clientes, String> colunaNumero;

	@FXML
	private TableColumn<Clientes, String> colunaComple;

	@FXML
	private TableColumn<Clientes, String> colunaBairro;

	@FXML
	private TableColumn<Clientes, String> colunaCidade;

	@FXML
	private TableColumn<Clientes, String> colunaUF;

	// criando um logger
	private static Logger logger = Logger.getLogger(CadastroClientes.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa
		 //obterUF();
//		listaClientes();

	}

	// metodo que cria o comboBox
	public void obterUF() {

		// criando um arrey de opções de UF
		String[] opcoesUF = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB",
				"PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
		/*
		 * adicionado lista opções de UF no atributo listaDeUF do Opções de Parcelas no
		 * formato ObservableList pq o comboBox só aceita um ObservableList
		 */ ObservableList<String> listaDeUF = FXCollections.observableArrayList(opcoesUF);

		// adicionando listaDeItemDeParcels no comboBox
		comboBoxUF.setItems(listaDeUF);

		/*
		 * adicionado um index 14 como padão no combBox para quando executar o metodo
		 * mauseCliqueNaTabelaClientes para não da um erro na coluna UF porque ela esta
		 * vazia
		 */comboBoxUF.getSelectionModel().select(14);

		logger.info("Lista de UF adicionada a o comboBoxUf aqui");
	}

	// metodo obter um lista clientes
	public void listaClientes() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Clientes, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Clientes, String>("rg"));

		colunaCPF.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cpf"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Clientes, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Clientes, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Clientes, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Clientes, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Clientes, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Clientes, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Clientes, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Clientes, String>("estado"));

		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Clientes> dao = new DAO<Clientes>(Clientes.class);

		// crinado um lista do tipo Cliente e adicionando um lista de Clientes
		List<Clientes> listaclinte = dao.obterTodos();

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Clientes> observaClientes = FXCollections.observableArrayList(listaclinte);
		// adicionado o ObservableList na tabela
		tabelaClientes.setItems(observaClientes);
		logger.info("Lista de clientes adicionada a tableview");
	}

	// metodo obter um lista clientes com o like
	public void listaClientesComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Clientes, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Clientes, String>("rg"));

		colunaCPF.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cpf"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Clientes, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Clientes, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Clientes, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Clientes, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Clientes, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Clientes, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Clientes, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Clientes, String>("estado"));

		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Clientes> dao = new DAO<Clientes>(Clientes.class);

		// crinado um lista do tipo Cliente com filtor e adicionando um listaclinte
		List<Clientes> listaclinte = dao.obterTodosComLike("nome", "%" + campoNomeConsulta.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Clientes> observaClientes = FXCollections.observableArrayList(listaclinte);
		// adicionado o ObservableList na tabela
		tabelaClientes.setItems(observaClientes);
		logger.info("Lista de clientes com filtro adicionada a tableview");
	}

	// metodo que altera um cliente
	@FXML
	void botaoEditar(ActionEvent event) {

		try {
			// crinado objeto cliente
			Clientes cliente = new Clientes();
			// pegando a opção selecionada no comboBox e colocando na variavel local uf
			String uf = comboBoxUF.getSelectionModel().getSelectedItem();

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			// criando dao do tipo clientes
			DAO<Clientes> dao = new DAO<>(Clientes.class);

			/*
			 * fazendo a consulta por codigo do cliente que é para ajuda a outra consuta que
			 * faz alteração que é essa que ta abaixo
			 */
			cliente = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");
			// fazendo uma segunad consulta pq so essa que consegi fazer a alteração
			cliente = dao.obterPorID(cliente.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");
			// abrindo a conexao com o banco pq sem ele não da para fazer a alteração
			dao.abrirTransacao();

			// fazendo as alterações no dado do cliente não é obrigatorio ta todos os campos
			// preenchido
			cliente.setNome(campoNome.getText());
			cliente.setRg(campoRG.getText());
			cliente.setCpf(campoCPF.getText());
			cliente.setEmail(campoEmail.getText());
			cliente.setTelefone(campoTelefone.getText());
			cliente.setCelular(campoCelular.getText());
			cliente.setCep(campoCEP.getText());
			cliente.setEndereco(campoEndereco.getText());
			cliente.setNumero(campoNumero.getText());
			cliente.setComplemento(campoComplemeto.getText());
			cliente.setBairro(campoBairro.getText());
			cliente.setCidade(campoCidade.getText());
			cliente.setEstado(uf);

			// fechando a conexao
			dao.fecharTransacao();

			dao.fechar();

			// Metodo que faz a limpeza dos campos do cadastro
			limpacampos();

			// finalizando alteração
			logger.info("Cliente Alterado com sucesso");

			/*
			 * metodo que chama a lista de Clientes para a tab consulta Clientes que é para
			 * quando altera o cliente ja exibi na tela de consulta
			 */listaClientes();
			logger.info("Consultando lista de Cliente com sucesso");
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Cliente Alterado com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Cliente não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Cliente não selecionado!");
			// chamando o alerta
			alertErro.show();

		}
	}

	// metodo que excluir um cliente
	@FXML
	void botaoExcluir(ActionEvent event) {

		try {

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			Clientes cliente = new Clientes();

			DAO<Clientes> dao = new DAO<>(Clientes.class);

			// fazendo a consulta por id
			cliente = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");

			// fazendo uma consulta que faz a exclusão por id
			cliente = dao.obterPorID(cliente.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");

//		// verificando se cliente é nulo
//		if (cliente != null) {

			// logger.info("Cliente encontrado");

			dao.abrirTransacao();

			// removendo Cliente
			dao.remover(cliente);//

			dao.fecharTransacao();

			logger.info("Cliente removido com sucesso");

			dao.fechar();

			// Metodo que faz a limpeza dos campos do cadastro
			limpacampos();

			/*
			 * metodo que chama a lista de Clientes para a tab consulta Clientes que é para
			 * quando altera o cliente ja exibi na tela de consulta
			 */listaClientes();
			logger.info("Consultando lista de Cliente com sucesso");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Cliente Excluidor com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Cliente não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Cliente não selecionado!");
			// chamando o alerta
			alertErro.show();
		}

	}

	// metodo limpar os campos
	@FXML
	void botaoNovo(ActionEvent event) {

		campoNome.setText(null);
		campoRG.setText(null);
		campoCPF.setText(null);
		campoTelefone.setText(null);
		campoCelular.setText(null);
		campoCEP.setText(null);
		campoEndereco.setText(null);
		campoNumero.setText(null);
		campoComplemeto.setText(null);
		campoBairro.setText(null);
		campoCidade.setText(null);
		campoCodigo.setText(null);
		campoEmail.setText(null);
	}

	@FXML
	void campoNomePesquisar(KeyEvent event) {
		// chamando a lista de clientes com filtro like
		listaClientesComLike();
	}

//	// metodo que pesquisa Clientes com filtro
//	@FXML
//	void botaoPesquisar(ActionEvent event) {
//		// chamando a lista de clientes com filtro like
//		listaClientesComLike();
//	}

	// metodo que salva um novo cliente
	@FXML
	void botaoSalvar(ActionEvent event) {

		// crinado um condição para o nome ser obrigatorio
		if (!campoNome.getText().equals("")) {
			
			//verificando se o cliente já esta cadastrado
			if(campoCodigo.getText().equals("")){
				
				try {
					// pegando a opção selecionada no comboBox e colocando na variavel local uf
					String uf = comboBoxUF.getSelectionModel().getSelectedItem();

					// criando o dao clientes para adiciona o clientes no banco
					DAO<Clientes> dao = new DAO<Clientes>();

					// adicionando os campos ao cliente
					Clientes cliente = new Clientes(campoNome.getText(), campoRG.getText(), campoCPF.getText(),
							campoEmail.getText(), campoTelefone.getText(), campoCelular.getText(), campoCEP.getText(),
							campoEndereco.getText(), campoNumero.getText(), campoComplemeto.getText(),
							campoBairro.getText(), campoCidade.getText(), uf);

					// gravando cliente no banco e fechndo conexão
					dao.incluirAtomico(cliente).fechar();

					logger.info("Cliente Cadastrado com Sucesso!");

					// Metodo que faz a limpeza dos campos do cadastro
					limpacampos();

					/*
					 * metodo que chama a lista de Clientes para a tab consulta cLiente para quando
					 * adicona um novo cliente ja exibi na tela de consulta
					 */
					listaClientes();

					// criando um alerta de confirmação
					// criando titulo do alerta
					alertInf.setTitle("Mensagem");
					// criando cabeçario do alerta
					alertInf.setHeaderText("Cadastrado com Sucesso!");
					// chamando o ale
					alertInf.show();

				} catch (Exception e) {

					logger.info("erro ao salvar cliente" + e);
					// criando um alerta de confirmação
					// criando titulo do alerta
					alertErro.setTitle("Erro");
					// criando cabeçario do alerta
					alertErro.setHeaderText("Erro ao salvar cliente!");
					// chamando o alerta
					alertErro.show();
				}
			//se ja estiver cadatrado mostra essa mensagens de erro 	
			}else {
				// finalizando alteração
				logger.info("Cliente já esta cadastrado");

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("Cliente já esta cadastrado");
				// chamando o alerta
				alertErro.show();				
			}
			
           //mesangens de erro quando nome estiver cadastrado 
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

	// metodo que faz a busca do endereço pelo cep
	@FXML
	void buscaCEP(KeyEvent event) {

		// condição que faz a busca quando preciona enter
		if (event.getCode() == KeyCode.ENTER) {

			CEP cep = new CEP();
			// metodo que faz a busca do cep
			cep.buscaCep(campoCEP.getText());

			campoBairro.setText(cep.getBairro());
			campoCidade.setText(cep.getCidade());
			campoEndereco.setText(cep.getEndereco());
			comboBoxUF.getSelectionModel().select(cep.getUf());

			logger.info("Endereco adiconado com sucesso!");

		} else {
			logger.info("Endereco não encontrado");
		}
	}

	// metodo que chama tela do menu principal
	@FXML
	void botaoMenu(ActionEvent event) {
		Main.trocaTela("menuPrincipal");
	}

	// metodo que lista os clinete ao pricioan o botaão
	@FXML
	void botaoLista(ActionEvent event) {
		listaClientes();
	}

	/*
	 * metodo que pega a linha selecionada da tabela view e mudar para a tab de
	 * dados pessoas assim que clica no botão
	 */
	@FXML
	void carregarSelecao(ActionEvent event) {

		// chamndo a tab do index 0 da TabPane que é a area do dados pessoas
		tabelaPane.getSelectionModel().select(0);
		logger.info("Chamando a Tab de index (0)");
		// pegando todos os campos da tabela view da linha celecionada e passando pra a
		// tela de tab dados pessoas
		campoCodigo.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCodigo().toString());
		campoNome.setText(tabelaClientes.getSelectionModel().getSelectedItem().getNome().toString());
		campoRG.setText(tabelaClientes.getSelectionModel().getSelectedItem().getRg().toString());
		campoCPF.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCpf().toString());
		campoEmail.setText(tabelaClientes.getSelectionModel().getSelectedItem().getEmail().toString());
		campoTelefone.setText(tabelaClientes.getSelectionModel().getSelectedItem().getTelefone().toString());
		campoCelular.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCelular().toString());
		campoCEP.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCep().toString());
		campoEndereco.setText(tabelaClientes.getSelectionModel().getSelectedItem().getEndereco().toString());
		campoNumero.setText(tabelaClientes.getSelectionModel().getSelectedItem().getNumero().toString());
		campoComplemeto.setText(tabelaClientes.getSelectionModel().getSelectedItem().getComplemento().toString());
		campoBairro.setText(tabelaClientes.getSelectionModel().getSelectedItem().getBairro().toString());
		campoCidade.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCidade().toString());
		comboBoxUF.setPromptText(tabelaClientes.getSelectionModel().getSelectedItem().getEstado().toString());
		/*
		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
		 */
		listaClientes();
		logger.info("Cliente adicionado com sucesso na tab dados pessoas");
	}

//	/*
//	 * metodo que pega a linha selecionada da tabela view e mudar para a tab de
//	 * dados pessoas assim que clica na linha da tableta view, mas não da para altera o tamanho da coluna 
//	 * pq fica trocando de tela 
//	 */
//	@FXML
//	void pegaDadosDaTabelaClientes(MouseEvent event) {
//
//		// chamndo a tab do index 0 da TabPane que é a area do dados pessoas
//		tabelaPane.getSelectionModel().select(0);
//		logger.info("Chamando a Tab de index (0)");
//		// pegando todos os campos da tabela view da linha celecionada e passando pra a
//		// tela de tab dados pessoas
//		campoCodigo.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCodigo().toString());
//		campoNome.setText(tabelaClientes.getSelectionModel().getSelectedItem().getNome().toString());
//		campoRG.setText(tabelaClientes.getSelectionModel().getSelectedItem().getRg().toString());
//		campoCPF.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCpf().toString());
//		campoEmail.setText(tabelaClientes.getSelectionModel().getSelectedItem().getEmail().toString());
//		campoTelefone.setText(tabelaClientes.getSelectionModel().getSelectedItem().getTelefone().toString());
//		campoCelular.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCelular().toString());
//		campoCEP.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCep().toString());
//		campoEndereco.setText(tabelaClientes.getSelectionModel().getSelectedItem().getEndereco().toString());
//		campoNumero.setText(tabelaClientes.getSelectionModel().getSelectedItem().getNumero().toString());
//		campoComplemeto.setText(tabelaClientes.getSelectionModel().getSelectedItem().getComplemento().toString());
//		campoBairro.setText(tabelaClientes.getSelectionModel().getSelectedItem().getBairro().toString());
//		campoCidade.setText(tabelaClientes.getSelectionModel().getSelectedItem().getCidade().toString());
//		comboBoxUF.setPromptText(tabelaClientes.getSelectionModel().getSelectedItem().getEstado().toString());
//		/*
//		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
//		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
//		 */
//		listaClientes();
//	}

	// metodos que cria uma mascara no campodo assim que o String for digitado
	@FXML
	void formataCampoCEP(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("#####-###");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoCEP);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoCPF(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("###.###.###-##");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoCPF);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoCelular(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("(##)#####-####");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoCelular);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoRG(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("#.###.###");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoRG);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoTelefone(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("(##)####-####");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoTelefone);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoCodigo(KeyEvent event) {

		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("######");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoCodigo);
		// formatando o campo
		tff.formatter();
	}

	@FXML
	void formataCampoNumero(KeyEvent event) {
		TextFieldFormatter tff = new TextFieldFormatter();
		// crinado a mascara
		tff.setMask("######");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoNumero);
		// formatando o campo
		tff.formatter();
	}

	// Metodo que faz a limpeza dos campos do cadastro
	void limpacampos() {

		campoNome.setText(null);
		campoRG.setText(null);
		campoCPF.setText(null);
		campoTelefone.setText(null);
		campoCelular.setText(null);
		campoCEP.setText(null);
		campoEndereco.setText(null);
		campoNumero.setText(null);
		campoComplemeto.setText(null);
		campoBairro.setText(null);
		campoCidade.setText(null);
		campoCodigo.setText(null);
		campoEmail.setText(null);
	}

//    @FXML
//    void teste1(ActionEvent event) {

//       listaClientes();
//       logger.info("teste1");
//    }

//    @FXML
//    void teste2(ActionEvent event) {
//    	listaClientes();
//        logger.info("teste2");
//    }
////
//    @FXML
//    void teste3(ActionEvent event) {
//    	listaClientes();
//        logger.info("teste3");
//    }

}
