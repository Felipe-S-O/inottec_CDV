package br.com.inottec.cdv.controlador;

import com.sun.istack.logging.Logger;

import br.com.inottec.cdv.Main;
import br.com.inottec.cdv.infra.DAO;
import br.com.inottec.cdv.modelo.Funcionarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TelaLogin {

	@FXML
	private TextField campoUsuario;

	@FXML
	private PasswordField campoSenha;

	@FXML
	private Button botaoEntrar;

	@FXML
	private Label mensagem;
	
	public static String operador;

	// criando um logger
	private static Logger logger = Logger.getLogger(TelaLogin.class);

	@FXML
	void keySenha(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			botaoEntrar.requestFocus();
		
		}

	}

	@FXML
	void keyUsuario(KeyEvent event) {

		if (event.getCode() == KeyCode.ENTER) {

			campoSenha.requestFocus();

		}
	}

	// metodo que valida usuario
	@FXML
	void botaoEntrar(ActionEvent event) {

		try {
			// crinado um condição para os campos ser obrigatorio
			if (!campoUsuario.getText().equals("") && !campoSenha.getText().equals("")) {

				// criando um dao
				DAO<Funcionarios> dao = new DAO<Funcionarios>(Funcionarios.class);

				// fazendo uma verificação
				Funcionarios funcionario = dao.validaUsuario("nome", "senha", campoUsuario.getText(),
						campoSenha.getText());

				// se o usuario for validado com sucesso
				if (campoUsuario.getText().equals(funcionario.getNome())
						&& campoSenha.getText().equals(funcionario.getSenha())) {
					
					operador = funcionario.getNome();

					Main tela = new Main();

					tela.criaTelaMenu();

					logger.info("Login efetuado com sucesso !");

				} else {
					logger.info("Parabéns você chegou em um local impocivel!!!");
				}
			}
		} catch (Exception e) {
			mensagem.setText("usuario ou senha incorreta");
		}

	}

}
