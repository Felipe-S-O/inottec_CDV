package br.com.inottec.cdv.controlador;

import java.io.IOException;

import br.com.inottec.cdv.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PosicaoDoDia {
	
	  @FXML
	  void botaoMenu(ActionEvent event) throws IOException {
		  
		  Main tela = new Main();

			tela.criaTelaMenu();

	  }

}
