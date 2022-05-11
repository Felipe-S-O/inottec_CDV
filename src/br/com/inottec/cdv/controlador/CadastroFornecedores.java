package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.mascaraText.TextFieldFormatter;
import br.com.inottec.cdv.modelo.CEP;
import br.com.inottec.cdv.modelo.Fornecedores;
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

public class CadastroFornecedores implements Initializable {

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
	private TextField campoCNPJ;

	@FXML
	private ComboBox<String> comboBoxUF;

	@FXML
	private TextField campoNomeConsulta;

	@FXML
	private TableView<Fornecedores> tabelaFornecedores;

	@FXML
	private TableColumn<Fornecedores, Long> colunaCodigo;

	@FXML
	private TableColumn<Fornecedores, String> colunaNome;

	@FXML
	private TableColumn<Fornecedores, String> colunaRG;

	@FXML
	private TableColumn<Fornecedores, String> colunaCNPJ;

	@FXML
	private TableColumn<Fornecedores, String> colunaEmail;

	@FXML
	private TableColumn<Fornecedores, String> colunaTelefone;

	@FXML
	private TableColumn<Fornecedores, String> colunaCelular;

	@FXML
	private TableColumn<Fornecedores, String> colunaCEP;

	@FXML
	private TableColumn<Fornecedores, String> colunaEndereco;

	@FXML
	private TableColumn<Fornecedores, String> colunaNumero;

	@FXML
	private TableColumn<Fornecedores, String> colunaComple;

	@FXML
	private TableColumn<Fornecedores, String> colunaBairro;

	@FXML
	private TableColumn<Fornecedores, String> colunaCidade;

	@FXML
	private TableColumn<Fornecedores, String> colunaUF;

	// criando um logger
	private static Logger logger = Logger.getLogger(CadastroFornecedores.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		// chamndo metodo para executa
		obterUF();
		campoCodigo.setEditable(false);
		//listaFornecedores();

		
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

	// metodo obter um lista Fornecedores
	public void listaFornecedores() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Fornecedores, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("rg"));

		colunaCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cnpj"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("email"));
	
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("estado"));

		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Fornecedores
		DAO<Fornecedores> dao = new DAO<Fornecedores>(Fornecedores.class);

		// crinado um lista do tipo Fornecedor e adicionando um lista de Fornecedores
		List<Fornecedores> listaFornecedores = dao.obterTodos();

		// crinado um lista do tipo ObservableList que recebe uma lista de Fornecedores
		ObservableList<Fornecedores> observaFornecedores = FXCollections.observableArrayList(listaFornecedores);
		// adicionado o ObservableList na tabela
		tabelaFornecedores.setItems(observaFornecedores);
		logger.info("Lista de clientes adicionada a tableview");
	}

	// metodo obter um lista Fornecedores com o like
	public void listaFornecedoresComLike() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Fornecedores, Long>("codigo"));

		colunaNome.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("nome"));

		colunaRG.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("rg"));

		colunaCNPJ.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cnpj"));

		colunaEmail.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("email"));

		colunaTelefone.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("telefone"));

		colunaCelular.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("celular"));

		colunaCEP.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cep"));

		colunaEndereco.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("endereco"));

		colunaNumero.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("numero"));

		colunaComple.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("complemento"));

		colunaBairro.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("bairro"));

		colunaCidade.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("cidade"));

		colunaUF.setCellValueFactory(new PropertyValueFactory<Fornecedores, String>("estado"));

		logger.info("Todos os Atributo de cliente forão adicionado na tablela de consulta");

		// crinado a Classe dao do tipo Fornecedores
		DAO<Fornecedores> dao = new DAO<Fornecedores>(Fornecedores.class);

		// crinado um lista do tipo Fornecedores com filtor e adicionando um listaFornecedores
		List<Fornecedores> listaFornecedores = dao.obterTodosComLike("nome", "%" + campoNomeConsulta.getText() + "%");

		// crinado um lista do tipo ObservableList que recebe uma lista de Fornecedores
		ObservableList<Fornecedores> observaFornecedores = FXCollections.observableArrayList(listaFornecedores);
		// adicionado o ObservableList na tabela
		tabelaFornecedores.setItems(observaFornecedores);
		logger.info("Lista de clientes com filtro adicionada a tableview");
	}

	// metodo que altera um Fornecedor
	@FXML
	void botaoEditar(ActionEvent event) {

		try {
			// crinado objeto Fornecedor
			Fornecedores fornecedores = new Fornecedores();
			// pegando a opção selecionada no comboBox e colocando na variavel local uf
			String uf = comboBoxUF.getSelectionModel().getSelectedItem();

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			// criando dao do tipo Fornecedores
			DAO<Fornecedores> dao = new DAO<>(Fornecedores.class);

			/*
			 * fazendo a consulta por codigo do Fornecedor que é para ajuda a outra consuta que
			 * faz alteração que é essa que ta abaixo
			 */
			fornecedores = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");
			// fazendo uma segunad consulta pq so essa que consegi fazer a alteração
			fornecedores = dao.obterPorID(fornecedores.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");
			// abrindo a conexao com o banco pq sem ele não da para fazer a alteração
			dao.abrirTransacao();

			// fazendo as alterações no dado do Fornecedor não é obrigatorio ta todos os campos
			// preenchido
			fornecedores.setNome(campoNome.getText());
			fornecedores.setRg(campoRG.getText());
			fornecedores.setCnpj(campoCNPJ.getText());
			fornecedores.setEmail(campoEmail.getText());
			fornecedores.setTelefone(campoTelefone.getText());
			fornecedores.setCelular(campoCelular.getText());
			fornecedores.setCep(campoCEP.getText());
			fornecedores.setEndereco(campoEndereco.getText());
			fornecedores.setNumero(campoNumero.getText());
			fornecedores.setComplemento(campoComplemeto.getText());
			fornecedores.setBairro(campoBairro.getText());
			fornecedores.setCidade(campoCidade.getText());
			fornecedores.setEstado(uf);

			// fechando a conexao
			dao.fecharTransacao();

			dao.fechar();
			
			limpaCampo();

			// finalizando alteração
			logger.info("Fornecedor Alterado com sucesso");
			/*
			 * metodo que chama a lista de Fornecedores para a tab consulta Fornecedores que é para
			 * quando altera o Fornecedores ja exibi na tela de consulta
			 */listaFornecedores();
			logger.info("Consultando lista de Fornecedores com sucesso");
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Fornecedor Alterado com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Fornecedor não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Fornecedor não selecionado!");
			// chamando o alerta
			alertErro.show();

		}
	}

	// metodo que excluir um Fornecedor
	@FXML
	void botaoExcluir(ActionEvent event) {

		try {

			// convertendo o campoCodigo para Long
			long codigo = Long.parseLong(campoCodigo.getText());

			Fornecedores fornecedores = new Fornecedores();

			DAO<Fornecedores> dao = new DAO<>(Fornecedores.class);

			// fazendo a consulta por id
			fornecedores = dao.consultarUm("codigo", codigo);
			logger.info("Consulta um por codigo efetuada com sucesso");

			// fazendo uma consulta que faz a exclusão por id
			fornecedores = dao.obterPorID(fornecedores.getCodigo());
			logger.info("Consulta por  ID efetuada com sucesso");

//		// verificando se Fornecedor é nulo
//		if (cliente != null) {

			// logger.info("Cliente encontrado");

			dao.abrirTransacao();

			// removendo Cliente
			dao.remover(fornecedores);//

			dao.fecharTransacao();

			logger.info("Fornecedor removido com sucesso");

			dao.fechar();
			
			limpaCampo();
			/*
			 * metodo que chama a lista de Fornecedores para a tab consulta Fornecedores que é para
			 * quando altera o Fornecedor ja exibi na tela de consulta
			 */listaFornecedores();
			logger.info("Consultando lista de Fornecedor com sucesso");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertInf.setTitle("Mensagem");
			// criando cabeçario do alerta
			alertInf.setHeaderText("Fornecedor Excluidor com sucesso!");
			// chamando o alerta
			alertInf.show();

		} catch (Exception e) {
			// finalizando alteração
			logger.info("Fornecedor não selecionado");

			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Fornecedor não selecionado!");
			// chamando o alerta
			alertErro.show();
		}

	}

	// metodo limpar os campos
	@FXML
	void botaoNovo(ActionEvent event) {

		limpaCampo();
	}

	@FXML
	void campoNomePesquisar(KeyEvent event) {
		// chamando a lista de Fornecedores com filtro like
		listaFornecedoresComLike();
	}

//	// metodo que pesquisa Fornecedores com filtro
//	@FXML
//	void botaoPesquisar(ActionEvent event) {
//		// chamando a lista de Fornecedores com filtro like
//		listaClientesComLike();
//	}

	// metodo que salva um novo Fornecedor
	@FXML
	void botaoSalvar(ActionEvent event) {

		// crinado um condição para o nome ser obrigatorio
		if (!campoNome.getText().equals("")) {
			
			if(campoCodigo.getText().equals("")) {
				
				try {

					// pegando a opção selecionada no comboBox e colocando na variavel local uf
					String uf = comboBoxUF.getSelectionModel().getSelectedItem();

					// criando o dao clientes para adiciona o clientes no banco
					DAO<Fornecedores> dao = new DAO<Fornecedores>();

					// adicionando os campos ao Fornecedor
					Fornecedores fornecedores = new Fornecedores(campoNome.getText(), campoRG.getText(), campoCNPJ.getText(),
							campoEmail.getText(), campoTelefone.getText(), campoCelular.getText(), campoCEP.getText(),
							campoEndereco.getText(), campoNumero.getText(), campoComplemeto.getText(),
							campoBairro.getText(), campoCidade.getText(), uf);

					// gravando Fornecedor no banco e fechndo conexão
					dao.incluirAtomico(fornecedores).fechar();

					logger.info("Fornecedor Cadastrado com Sucesso!");

					/*
					 * metodo que chama a lista de Fornecedores para a tab consulta Fornecedor para quando
					 * adicona um novo Fornecedor ja exibi na tela de consulta
					 */
					
					limpaCampo();
					
					listaFornecedores();

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

			}else {
				
				// finalizando alteração
				logger.info("Fornecedor já esta cadastrado");

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertErro.setTitle("Erro");
				// criando cabeçario do alerta
				alertErro.setHeaderText("Fornecedor já esta cadastrado!");
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
		campoCodigo.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getCodigo().toString());
		campoNome.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getNome().toString());
		campoRG.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getRg().toString());
		campoCNPJ.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getCnpj().toString());
		campoEmail.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getEmail().toString());
		campoTelefone.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getTelefone().toString());
		campoCelular.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getCelular().toString());
		campoCEP.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getCep().toString());
		campoEndereco.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getEndereco().toString());
		campoNumero.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getNumero().toString());
		campoComplemeto.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getComplemento().toString());
		campoBairro.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getBairro().toString());
		campoCidade.setText(tabelaFornecedores.getSelectionModel().getSelectedItem().getCidade().toString());
		comboBoxUF.setPromptText(tabelaFornecedores.getSelectionModel().getSelectedItem().getEstado().toString());
		/*
		 * listando Fornecedor porque quando uso o metodo listaFornecedoresComLike ele deixa a
		 * tabela com o filtro e o metodo listaClientes traz a tabela de volta ao normal
		 */
		listaFornecedores();
		logger.info("Fornecedor adicionado com sucesso na tab dados pessoas");
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
    @FXML
    void botaoListaTodos(ActionEvent event) {

    	listaFornecedores();
    }

    @FXML
    void botaoMenu(ActionEvent event) {

    	Main.trocaTela("menuPrincipal");
    }

	
	//metodo que limpa os campos
	void limpaCampo () {
		
		campoNome.setText(null);
		campoRG.setText(null);
		campoCNPJ.setText(null);
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
		tff.setMask("##.###.###./####-##");
		// os numeros que pode ser colocado
		tff.setCaracteresValidos("0123456789");
		// o campo que vai receber a formatação
		tff.setTf(campoCNPJ);
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
}
