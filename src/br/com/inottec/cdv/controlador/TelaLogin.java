package br.com.inottec.cdv.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.istack.logging.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Funcionarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin implements Initializable {

	@FXML
	private TextField campoUsuario;

	@FXML
	private PasswordField campoSenha;

	// criando um logger
	private static Logger logger = Logger.getLogger(TelaLogin.class);

	// crinado uma mensagem de informação do tipo alerta
	private Alert alertInf = new Alert(Alert.AlertType.INFORMATION);

	// crinado uma mensagem de erro do tipo alerta
	private Alert alertErro = new Alert(Alert.AlertType.ERROR);

	// metodo que inicializa o comboBox
	public void initialize(URL location, ResourceBundle resources) {
		
	  campoUsuario.requestFocus();

	}

	// metodo que valida usuario
	@FXML
	void botaoEntrar(ActionEvent event) {

		// crinado um condição para os campos ser obrigatorio
		if (!campoUsuario.getText().equals("") && !campoSenha.getText().equals("")) {

			// criando um dao
			DAO<Funcionarios> dao = new DAO<Funcionarios>(Funcionarios.class);

			// fazendo uma verificação
			Funcionarios funcionario = dao.validaUsuario("nome", "senha", campoUsuario.getText(), campoSenha.getText());

			// se o usuario for validado com sucesso
			if (campoUsuario.getText().equals(funcionario.getNome())
					&& campoSenha.getText().equals(funcionario.getSenha())) {

				// criando um alerta de confirmação
				// criando titulo do alerta
				alertInf.setTitle("Mensagem");
				// criando cabeçario do alerta
				alertInf.setHeaderText("Login efetuado com sucesso !");
				// chamando o alerta
				alertInf.show();

				Main.trocaTela("menuPrincipal");

				logger.info("sucesso");
			} else {
				logger.info("Parabéns você chegou em um local impocivel!!!");
			}

		} else {
			// criando um alerta de confirmação
			// criando titulo do alerta
			alertErro.setTitle("Erro");
			// criando cabeçario do alerta
			alertErro.setHeaderText("Campo nome é obrigatorio!");
			// chamando o alerta
			alertErro.show();

		}
	}

}
