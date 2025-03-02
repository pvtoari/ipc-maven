package net.pvtoari.ipcmaven.pract1.loginIPC;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField texto_usuario;

    @FXML
    private Text mensaje_usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pulsadoIniciar(ActionEvent event) {
        mensaje_usuario.setText("Bienvenido " + texto_usuario.getText());
    }
}
