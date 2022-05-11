package br.com.inottec.cdv.controlador;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Produtos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PontoDeVenda {

	private Produtos produto;

	private List<Produtos> produtos = new ArrayList<>();

	private DAO<Produtos> dao = new DAO<Produtos>(Produtos.class);

//	// criando um logger
//	private static Logger logger = Logger.getLogger(CadastroClientes.class);
//
//	// crinado uma mensagem de informação do tipo alerta
//	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	@FXML
	private TextField campoBuscarProduto;

	@FXML
	private TextField campoQuantidade;

	@FXML
	private TextField campoValorTotal;

	@FXML
	private TableView<Produtos> tabelaProduto;

	@FXML
	private TableColumn<Produtos, Long> colunaCodigo;

	@FXML
	private TableColumn<Produtos, Double> colunaPreco;

	@FXML
	private TableColumn<Produtos, String> colunaProduto;

	@FXML
	private TableColumn<Produtos, Integer> colunaQtd;

	@FXML
	private TableColumn<Produtos, Double> colunaSubtotal;

	@FXML
	private Label textoData;

	@FXML
	void botaoMenu(ActionEvent event) {

		Main.trocaTela("menuPrincipal");
	}

	@FXML
	void botaoPagamento(ActionEvent event) {

		if(!produtos.equals(null)) {
			Main.trocaTela("");
		}
	}
	
    @FXML
    void botaoPesquisar(ActionEvent event) {
    	Main.trocaTela("telaPesquisarProduto");
    }

	@FXML
	void buscarProduto(KeyEvent event) {

		// condição que faz a adiciona produto preciona enter
		if (event.getCode() == KeyCode.ENTER) {
			
			adicionarProduto();

		} else if (event.getCode() == KeyCode.P) {

			Main.trocaTela("telaPesquisarProduto");
		}

	}

	// metodo obter um lista clientes com o like
	public void listaCarrinho() {

		/*
		 * adicionado coluna do banco a coluna da tabela o nome da coluna do banco é o
		 * mesmo da classe entidade
		 */
		colunaCodigo.setCellValueFactory(new PropertyValueFactory<Produtos, Long>("codigo"));

		colunaPreco.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("preco"));

		colunaProduto.setCellValueFactory(new PropertyValueFactory<Produtos, String>("descricao"));

		colunaQtd.setCellValueFactory(new PropertyValueFactory<Produtos, Integer>("qtdEstoque"));

		colunaSubtotal.setCellValueFactory(new PropertyValueFactory<Produtos, Double>("subtotal"));

		// crinado um lista do tipo ObservableList que recebe uma lista de clientes
		ObservableList<Produtos> observaCarrinho = FXCollections.observableArrayList(produtos);
		// adicionado o ObservableList na tabela
		tabelaProduto.setItems(observaCarrinho);
		// logger.info("Lista de clientes com filtro adicionada a tableview");
	}

	private void adicionarProduto() {
		try {

			if (campoBuscarProduto.getText().equals("") || campoBuscarProduto.getText().equals(null)) {

				Long idProduto = Long.parseLong(campoBuscarProduto.getText());

				produto = dao.obterPorID(idProduto);
				
				produto.setQtdEstoque(Integer.parseInt(campoQuantidade.getText()));
				
			    double subtotal =  produto.getQtdEstoque() * produto.getPreco();
				
				produto.setSubtotal(subtotal);

				produtos.add(produto);

				listaCarrinho();
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

}