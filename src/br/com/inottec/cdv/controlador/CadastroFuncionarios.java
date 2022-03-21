package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.mascaraText.TextFieldFormatter;
import br.com.inottec.cdv.modelo.CEP;
import br.com.inottec.cdv.modelo.Funcionarios;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;

public class CadastroFuncionarios implements Initializable {

	@FXML
	private TabPane tabelaPane;

	@FXML
	private TextField campoCargo;

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
	private ComboBox<String> comboBoxNivelDeAcesso;

	@FXML
	private TextField campoComplemeto1;

	@FXML
	private TextField campoSenha;

	@FXML
	private TextField campoConfirmaSenha;

	@FXML
	private TextField campoNomeConsulta;

	@FXML
	private TableView<Funcionarios> tabelaFuncionario;

	@FXML
	private TableColumn<Funcionarios, Long> colunaCodigo;

	@FXML
	private TableColumn<Funcionarios, String> colunaNome;

	@FXML
	private TableColumn<Funcionarios, String> colunaRG;

	@FXML
	private TableColumn<Funcionarios, String> colunaCPF;

	@FXML
	private TableColumn<Funcionarios, String> colunaEmail;

	@FXML
	private TableColumn<Funcionarios, String> colunaSenha;

	@FXML
	private TableColumn<Funcionarios, String> colunaCargo;

	@FXML
	private TableColumn<Funcionarios, String> colunaNivelDeAcesso;

	@FXML
	private TableColumn<Funcionarios, String> colunaTelefone;

	@FXML
	private TableColumn<Funcionarios, String> colunaCelular;

	@FXML
	private TableColumn<Funcionarios, String> colunaCEP;

	@FXML
	private TableColumn<Funcionarios, String> colunaEndereco;

	@FXML
	private TableColumn<Funcionarios, String> colunaNumero;

	@FXML
	private TableColumn<Funcionarios, String> colunaComple;

	@FXML
	private TableColumn<Funcionarios, String> colunaBairro;

	@FXML
	private TableColumn<Funcionarios, String> colunaCidade;

	@FXML
	private TableColumn<Funcionarios, String> colunaUF;

	// criando um logger
	private static Logger logger = Logger.getLogger(CadastroFuncionarios.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa
		//obterUF();
		//obterNivelDeAcesso();
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

		logger.info("Lista de UF adicionada a o comboBoxUf");
	}

	// metodo que cria o comboBox
	public void obterNivelDeAcesso() {

		// criando um arrey de opções de UF
		String[] opcoesNvelDeAesso = { "Vendedor", "Admin" };
		/*
		 * adicionado lista opções de UF no atributo listaDeUF do Opções de Parcelas no
		 * formato ObservableList pq o comboBox só aceita um ObservableList
		 */ ObservableList<String> listaDeNiveDeAcesso = FXCollections.observableArrayList(opcoesNvelDeAesso);

		// adicionando listaDeItemDeParcels no comboBox
		comboBoxNivelDeAcesso.setItems(listaDeNiveDeAcesso);

		/*
		 * adicionado um index 0 como padão no combBox para quando executar o metodo
		 * mauseCliqueNaTabelaClientes para não da um erro na coluna NivelDeAcesso
		 * porque ela esta vazia
		 */comboBoxNivelDeAcesso.getSelectionModel().select(1);

		logger.info("Lista de UF adicionada a o comboBoxNivelDeAcesso");
	}

	// metodo obter um lista clientes
	public void listaFuncionarios() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Funcionarios, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("rg"));

		colunaCPF.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cpf"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("email"));

		colunaSenha.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("senha"));

		colunaCargo.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cargo"));

		colunaNivelDeAcesso.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("nivelDeAcesso"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("estado"));

		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		DAO<Funcionarios> dao = new DAO<Funcionarios>(Funcionarios.class);

		List<Funcionarios> listaFuncionario = dao.obterTodos();

		ObservableList<Funcionarios> observaFuncionaro = FXCollections.observableArrayList(listaFuncionario);

		tabelaFuncionario.setItems(observaFuncionaro);

		logger.info("Lista de funcionario adicionada a tableview");
//		//try {
//
//			// crinado a Classe dao do tipo Clinetes
//			DAO<Funcionarios> dao = new DAO<Funcionarios>(Funcionarios.class);
//
//			// crinado um lista do tipo Cliente e adicionando um lista de Clientes
//			List<Funcionarios> listafuncionario = dao.obterTodos();
//
//			// crinado um lista do tipo ObservableList que recebe uma lista de clientes
//			ObservableList<Funcionarios> observaFuncionario = FXCollections.observableArrayList(listafuncionario);
//			// adicionado o ObservableList na tabela
//			tabelaFuncionario.setItems(observaFuncionario);
//			
//		//} catch (Exception e) {
//			logger.info("Opes algo de errado");
//		//}
	}

	// metodo obter um lista clientes com o like
	public void listaFuncionariosComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Funcionarios, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("rg"));

		colunaCPF.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cpf"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Funcionarios, String>("estado"));

		logger.info("Todos os Atributo de funcionario forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Clinetes
		DAO<Funcionarios> dao = new DAO<Funcionarios>(Funcionarios.class);

		// crinado um lista do tipo Cliente com filtor e adicionando um listaclinte
		List<Funcionarios> listaclinte = dao.obterTodosComLike("nome", "%" + campoNomeConsulta.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Funcionarios> observaClientes = FXCollections.observableArrayList(listaclinte);
		// adicionado o ObservableList na tabela
		tabelaFuncionario.setItems(observaClientes);
		logger.info("Lista de funcionario com filtro adicionada a tableview");
	}

	// metodo que altera um funcionario
	@FXML
	void botaoEditar(ActionEvent event) {

		try {
			// crinado objeto Funcuinario
			Funcionarios funcuinario = new Funcionarios();
			// pegando a opção selecionada no comboBox e colocando na variavel local uf
			String uf = comboBoxUF.getSelectionModel().getSelectedItem();

			// pegando a opção selecionada no comboBoxNivelDeAcesso e colocando na variavel
			// local nivelAcesso
			String nivelAcesso = comboBoxNivelDeAcesso.getSelectionModel().getSelectedItem();

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			// criando dao do tipo Funcuinario
			DAO<Funcionarios> dao = new DAO<>(Funcionarios.class);

			/*
			 * fazendo a consulta por codigo do cliente que é para ajuda a outra consuta que
			 * faz alteração que é essa que ta abaixo
			 */
			funcuinario = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");
			// fazendo uma segunad consulta pq so essa que consegi fazer a alteração
			funcuinario = dao.obterPorID(funcuinario.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");
			// abrindo a conexao com o banco pq sem ele não da para fazer a alteração
			dao.abrirTransacao();

			// fazendo as alterações no dado do cliente não é obrigatorio ta todos os campos
			// preenchido
			funcuinario.setNome(campoNome.getText());
			funcuinario.setRg(campoRG.getText());
			funcuinario.setCpf(campoCPF.getText());
			funcuinario.setEmail(campoEmail.getText());
			funcuinario.setSenha(campoSenha.getText());
			funcuinario.setCargo(campoCargo.getText());
			funcuinario.setNivelDeAcesso(nivelAcesso);
			funcuinario.setTelefone(campoTelefone.getText());
			funcuinario.setCelular(campoCelular.getText());
			funcuinario.setCep(campoCEP.getText());
			funcuinario.setEndereco(campoEndereco.getText());
			funcuinario.setNumero(campoNumero.getText());
			funcuinario.setComplemento(campoComplemeto.getText());
			funcuinario.setBairro(campoBairro.getText());
			funcuinario.setCidade(campoCidade.getText());
			funcuinario.setEstado(uf);

			// fechando a conexao
			dao.fecharTransacao();

			dao.fechar();

			limpaCampo();

			// finalizando alteração
			logger.info("Funcuinario Alterado com sucesso");

			/*
			 * metodo que chama a lista de Clientes para a tab consulta Clientes que é para
			 * quando altera o cliente ja exibi na tela de consulta
			 */listaFuncionarios();
			logger.info("Consultando lista de Funcuinario com sucesso");
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Funcuinário Alterado com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Funcuinario não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Funcuinário não selecionado!");
			// chamando o alerta
			alertErro.show();

		}
	}

	// metodo que excluir funcionario
	@FXML
	void botaoExcluir(ActionEvent event) {

		try {

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			// crinado objeto Funcuinario
			Funcionarios funcuinario = new Funcionarios();

			// criando dao do tipo Funcuinario
			DAO<Funcionarios> dao = new DAO<>(Funcionarios.class);

			// fazendo a consulta por id
			funcuinario = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");

			// fazendo uma consulta que faz a exclusão por id
			funcuinario = dao.obterPorID(funcuinario.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");

//		// verificando se cliente é nulo
//		if (cliente != null) {

			// logger.info("Cliente encontrado");

			dao.abrirTransacao();

			// removendo Cliente
			dao.remover(funcuinario);//

			dao.fecharTransacao();

			logger.info("funcionario removido com sucesso");

			dao.fechar();

			limpaCampo();

			/*
			 * metodo que chama a lista de funcionario para a tab consulta funcionario que é
			 * para quando altera o cliente ja exibi na tela de consulta
			 */listaFuncionarios();
			logger.info("Consultando lista de funcionario com sucesso");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Funcionario Excluidor com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Funcionario não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Funcionário não selecionado!");
			// chamando o alerta
			alertErro.show();
		}

	}

	// metodo que limpa os campos
	@FXML
	void botaoNovo(ActionEvent event) {

		// limpando todos os campos alterando para nulo
		limpaCampo();
	}

	@FXML
	void botaoMenu(ActionEvent event) {

		Main.trocaTela("menuPrincipal");
	}

	@FXML
	void botaoPesquisar(ActionEvent event) {
		// chamando a lista de Funcionario com filtro like
		listaFuncionariosComLike();
	}

	@FXML
	void botaoSalvar(ActionEvent event) {

		// crinado um condição para o nome ser obrigatorio
		if (!campoNome.getText().equals("")) {
			
			if(campoCodigo.getText().equals("")) {
				
				try {
					// pegando a opção selecionada no comboBox e colocando na variavel local uf
					String uf = comboBoxUF.getSelectionModel().getSelectedItem();

					// pegando a opção selecionada no comboBox e colocando na variavel local uf
					String nivelDeAcesso = comboBoxNivelDeAcesso.getSelectionModel().getSelectedItem();

					// criando o dao clientes para adiciona o clientes no banco
					DAO<Funcionarios> dao = new DAO<Funcionarios>();

					// adicionando os campos ao cliente
					Funcionarios funcionario = new Funcionarios(campoNome.getText(), campoRG.getText(), campoCPF.getText(),
							campoEmail.getText(), campoSenha.getText(), campoCargo.getText(), nivelDeAcesso,
							campoTelefone.getText(), campoCelular.getText(), campoCEP.getText(), campoEndereco.getText(),
							campoNumero.getText(), campoComplemeto.getText(), campoBairro.getText(), campoCidade.getText(),
							uf);

					// gravando cliente no banco e fechndo conexão
					dao.incluirAtomico(funcionario).fechar();

					logger.info("Cliente Cadastrado com Sucesso!");

					limpaCampo();

					/*
					 * metodo que chama a lista de funcionario para a tab consulta funcionario para
					 * quando adicona um novo funcionario ja exibi na tela de consulta
					 */
					listaFuncionarios();


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
					alertErro.setHeaderText("Erro ao salvar funcionário!");
					// chamando o alerta
					alertErro.show();
				}
				
			}else {				
				// finalizando alteração
				logger.info("Funcionario já esta cadastrado");

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("Funcionario já esta cadastrado!");
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

	// metodo que pesquisar funcionario
	@FXML
	void campoNomePesquisar(KeyEvent event) {
		// chamando a lista de funcionario com filtro like
		listaFuncionariosComLike();
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
		campoCodigo.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCodigo().toString());
		campoNome.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getNome().toString());
		campoRG.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getRg().toString());
		campoCPF.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCpf().toString());
		campoEmail.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEmail().toString());
		campoSenha.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getSenha().toString());
		campoCargo.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCargo().toString());
		comboBoxNivelDeAcesso
				.setPromptText(tabelaFuncionario.getSelectionModel().getSelectedItem().getNivelDeAcesso().toString());
		campoTelefone.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getTelefone().toString());
		campoCelular.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCelular().toString());
		campoCEP.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCep().toString());
		campoEndereco.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEndereco().toString());
		campoNumero.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getNumero().toString());
		campoComplemeto.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getComplemento().toString());
		campoBairro.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getBairro().toString());
		campoCidade.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCidade().toString());
		comboBoxUF.setPromptText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEstado().toString());
		/*
		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
		 */
		listaFuncionarios();
		logger.info("Cliente adicionado com sucesso na tab dados pessoas");
	}

	@FXML
	void listaTodos(ActionEvent event) {

		listaFuncionarios();
	}

	void limpaCampo() {

		// limpando todos os campos alterando para nulo
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
		campoCargo.setText(null);
		campoSenha.setText(null);

	}

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

///*
//	 * metodo que pega a linha selecionada da tabela view e mudar para a tab de
//	 * dados pessoas assim que clica na linha da tableta view, mas não da para altera o tamanho da coluna 
//	 * pq fica trocando de tela 
//	 */
//	@FXML
//	void pegaDadosDaTabelaFuncionario(MouseEvent event) {
//
//		// chamndo a tab do index 0 da TabPane que é a area do dados pessoas
//		tabelaPane.getSelectionModel().select(0);
//		logger.info("Chamando a Tab de index (0)");
//		// pegando todos os campos da tabela view da linha celecionada e passando pra a
//		// tela de tab dados pessoas
//		campoCodigo.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCodigo().toString());
//		campoNome.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getNome().toString());
//		campoRG.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getRg().toString());
//		campoCPF.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCpf().toString());
//		campoEmail.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEmail().toString());
//		campoTelefone.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getTelefone().toString());
//		campoCelular.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCelular().toString());
//		campoCEP.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCep().toString());
//		campoEndereco.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEndereco().toString());
//		campoNumero.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getNumero().toString());
//		campoComplemeto.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getComplemento().toString());
//		campoBairro.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getBairro().toString());
//		campoCidade.setText(tabelaFuncionario.getSelectionModel().getSelectedItem().getCidade().toString());
//		comboBoxUF.setPromptText(tabelaFuncionario.getSelectionModel().getSelectedItem().getEstado().toString());
//		/*
//		 * listando cliente porque quando uso o metodo listaClientesComLike ele deixa a
//		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
//		 */
//		listaFuncionarios();
//	}
}
