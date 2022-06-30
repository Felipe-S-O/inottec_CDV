package br.com.inottec.cdv;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	private static Stage janela;
	private static Scene cenaTelaLogin;
	private static Scene cenaTelaProduto;
	private static Scene cenaTelaClientes;
	private static Scene cenaTelaFuncionario;
	private static Scene cenaTelaFornecedor;
	private static Scene cenaTelaPDV;
	private static Scene cenaTelaHistoricoDeVendas;
	private static Scene cenaTelaPosicaoDoDia;
	private static Scene cenaTelaPagamento;
	private static Scene cenaTelaIdentificar;
	private static Scene cenaTelaPesquisarProduto;
	private static Scene cenaTelaMenuPrincipal; 

	
	@Override
	public void start(Stage primaryStage) throws IOException {

		janela = primaryStage;


		// comando para usuario não pode altera o tamanho da tela
		janela.setResizable(false);

		// criando titulo
		janela.setTitle("inottec CDV");

		janela.getIcons().add(new Image("/view/imagens/logo.png"));
		
		
//		criaTelaLogin();
		
		criaTelaPDV();
		// executando cenaro
	//	janela.setScene(cenaTelaLogin);
		// executando a tela
		janela.show();

	}

	public void criaTelaLogin() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaLogin = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaLogin = new Scene(fxmlTelaLogin, 633, 379);
	
		// executando cenaro
		janela.setScene(cenaTelaLogin);


	}

	public void criaTelaMenu() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaMenuPrincipal = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaMenuPrincipal = new Scene(fxmlTelaMenuPrincipal, 1041, 690);		
		
		
		janela.setResizable(false);

		// criando titulo
		janela.setTitle("inottec CDV");

		janela.getIcons().add(new Image("/view/imagens/logo.png"));
		
		
		// executando cenaro
		janela.setScene(cenaTelaMenuPrincipal);
		// executando a tela
		janela.show();

	}

	public void criaTelaPDV() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPDV = FXMLLoader.load(getClass().getResource("/view/PontoDeVenda.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPDV = new Scene(fxmlTelaPDV, 1066, 690);

		janela.setScene(cenaTelaPDV);

		
	}
	

	public void criaTelaPagamento() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPagamento = FXMLLoader.load(getClass().getResource("/view/TelaPagamento.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPagamento = new Scene(fxmlTelaPagamento, 289, 417);

		janela.setScene(cenaTelaPagamento);

	}

	public void criaTelaCliente() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaClientes = FXMLLoader.load(getClass().getResource("/view/CadastroClientes.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaClientes = new Scene(fxmlTelaClientes, 1041, 523);

		janela.setScene(cenaTelaClientes);

	}

	public void criaTelaFuncionario() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaFuncionario = FXMLLoader.load(getClass().getResource("/view/CadastroFuncionario.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaFuncionario = new Scene(fxmlTelaFuncionario, 1041, 573);

		janela.setScene(cenaTelaFuncionario);

	}

	public void criaTelaFornecedor() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaFornecedor = FXMLLoader.load(getClass().getResource("/view/CadastroFornecedores.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaFornecedor = new Scene(fxmlTelaFornecedor, 1041, 523);

		janela.setScene(cenaTelaFornecedor);

	}

	public void criaTelaProduto() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaProduto = FXMLLoader.load(getClass().getResource("/view/CadastroProduto.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaProduto = new Scene(fxmlTelaProduto, 1041, 523);

		janela.setScene(cenaTelaProduto);

	}

	public void criaTelaHistoricoDeVendas() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaHistoricoDeVendas = FXMLLoader.load(getClass().getResource("/view/HistoricoDeVendas.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaHistoricoDeVendas = new Scene(fxmlTelaHistoricoDeVendas, 1041, 523);

		janela.setScene(cenaTelaHistoricoDeVendas);

	}

	public void criaTelaPosicaoDoDia() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPosicaoDoDia = FXMLLoader.load(getClass().getResource("/view/PosicaoDoDia.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPosicaoDoDia = new Scene(fxmlTelaPosicaoDoDia, 1041, 523);

		janela.setScene(cenaTelaPosicaoDoDia);

	}

	public void criaTelaPesquisarProduto() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPesquisarProduto = FXMLLoader.load(getClass().getResource("/view/PesquisarProduto.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPesquisarProduto = new Scene(fxmlTelaPesquisarProduto, 1041, 523);

		janela.setScene(cenaTelaPesquisarProduto);

	}

	public void criaTelaIdentificar() throws IOException {

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaIndetificar = FXMLLoader.load(getClass().getResource("/view/IndentificaClientes.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaIdentificar = new Scene(fxmlTelaIndetificar, 1041, 523);

		janela.setScene(cenaTelaIdentificar);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
