package br.com.inottec.cdv;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
	private static Scene cenaTelaPesquisarProduto;
	private static Scene cenaTelaMenuPrincipal;

	@Override
	public void start(Stage primaryStage) throws IOException {

		janela = primaryStage;

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaLogin = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaLogin = new Scene(fxmlTelaLogin, 592, 288);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaProduto = FXMLLoader.load(getClass().getResource("/view/CadastroProduto.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaProduto = new Scene(fxmlTelaProduto, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaClientes = FXMLLoader.load(getClass().getResource("/view/CadastroClientes.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaClientes = new Scene(fxmlTelaClientes, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaFuncionario = FXMLLoader.load(getClass().getResource("/view/CadastroFuncionario.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaFuncionario = new Scene(fxmlTelaFuncionario, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaFornecedor = FXMLLoader.load(getClass().getResource("/view/CadastroFornecedores.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaFornecedor = new Scene(fxmlTelaFornecedor, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPDV = FXMLLoader.load(getClass().getResource("/view/PontoDeVenda.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPDV = new Scene(fxmlTelaPDV, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaHistoricoDeVendas = FXMLLoader.load(getClass().getResource("/view/HistoricoDeVendas.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaHistoricoDeVendas = new Scene(fxmlTelaHistoricoDeVendas, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPosicaoDoDia = FXMLLoader.load(getClass().getResource("/view/PosicaoDoDia.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPosicaoDoDia = new Scene(fxmlTelaPosicaoDoDia, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPagamento = FXMLLoader.load(getClass().getResource("/view/TelaPagamento.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPagamento = new Scene(fxmlTelaPagamento, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaPesquisarProduto = FXMLLoader.load(getClass().getResource("/view/PesquisarProduto.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaPesquisarProduto = new Scene(fxmlTelaPesquisarProduto, 1041, 523);

		// pega o arquivo fxml e converte para gridpane
		Parent fxmlTelaMenuPrincipal = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
		// criando um senario usando o arquivo fxml
		cenaTelaMenuPrincipal = new Scene(fxmlTelaMenuPrincipal, 1041, 523);

		// comando para usuario não pode altera o tamanho da tela
		janela.setResizable(false);
		// criando titulo
		janela.setTitle("inottec CDV");

		// executando cenaro
		janela.setScene(cenaTelaMenuPrincipal);
		// executando a tela
		janela.show();
	}

	// criando metodo statico para troca tela com parametro do tipo string
	public static void trocaTela(String tela) {

		switch (tela) {
		// se o parametro for telaLogin chama o cenaTelaLogin
		case "telaLogin":

//			// remove a barra de titulo
//			janela.initStyle(StageStyle.UNDECORATED);
//			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
			// centralizando a tela
			janela.centerOnScreen();
//			// criando titulo
//			janela.setTitle("inottec-CDV");
			// executando cenaro
			janela.setScene(cenaTelaLogin);
//			// executando a tela
//			janela.show();
			break;

		// se o parametro for telaClientes chama o cenaTelaClientes
		case "telaClientes":

//			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//			// criando titulo
//			janela.setTitle("Cadastro Clientes");
			// executando cenaro
			janela.setScene(cenaTelaClientes);
//			// executando a tela
//			janela.show();
			break;

		// se o parametro for telaFuncionario chama o cenaTelaFuncionario
		case "telaFuncionario":

//			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//			// criando titulo
//			janela.setTitle("Cadastro Clientes");
			// executando cenaro
			janela.setScene(cenaTelaFuncionario);
//			// executando a tela
//			janela.show();
			break;

		// se o parametro for telaFuncionario chama o cenaTelaFornecedor
		case "telaFornecedor":

//			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//			// criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaFornecedor);
//			// executando a tela
//			janela.show();
			break;

		// se o parametro for telaFuncionario chama o cenaTelaProduto
		case "telaProduto":

//			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//			// criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaProduto);
//			// executando a tela
//			janela.show();
			break;

		// se o parametro for telaFuncionario chama o cenaTelaProduto
		case "telaPDV":

//	    // comando para usuario não pode altera o tamanho da telaadiciona Gasto
//		janela.setResizable(false);
//	    // criando titulo
//		janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaPDV);
//						// executando a tela
//						janela.show();
			break;

		case "telaHistoricoDeVendas":

//		    // comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//		    // criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaHistoricoDeVendas);
//							// executando a tela
//							janela.show();
			break;
			
		case "telaPosicaoDoDia":

//		    // comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//		    // criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaPosicaoDoDia);
//							// executando a tela
//							janela.show();
			break;
		case "telaPesquisarProduto":

//		    // comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//		    // criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaPesquisarProduto);
//							// executando a tela
//							janela.show();
			break;
			
		case "telaPagamento":

//		    // comando para usuario não pode altera o tamanho da telaadiciona Gasto
//			janela.setResizable(false);
//		    // criando titulo
//			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaPagamento);
//							// executando a tela
//							janela.show();
			break;

		// se o parametro for menuPrincipal chama o cenaTelaMenuPrincipal
		case "menuPrincipal":

			// criando titulo
			janela.setTitle("inottec CDV");
			// executando cenaro
			janela.setScene(cenaTelaMenuPrincipal);
			// centralizando a tela
			janela.centerOnScreen();
			// comando para usuario não pode altera o tamanho da telaadiciona Gasto
			janela.setResizable(false);
//			// remove a barra de titulo
//			janela1.initStyle(StageStyle.DECORATED);
			janela.setFullScreen(true);
			// executando a tela
			janela.show();
			break;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
