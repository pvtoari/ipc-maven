package net.pvtoari.ipcmaven.pract7.menudialogstoolbarsIPC.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class MainViewController implements Initializable {
    private final String AMAZON_URL = "https://www.amazon.es/";
    private final String EBAY_URL = "https://www.ebay.es/";

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private Button amazonButton, bloggerButton, ebayButton, facebookButton, googlePlusButton;

    @FXML
    private RadioMenuItem amazonRadioMenuItem, ebayRadioMenuItem;

    @FXML
    Label visitingLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.exitMenuItem.setOnAction(this::exitItemOnAction);
        this.amazonButton.setOnAction(this::amazonButtonOnAction);
        this.ebayButton.setOnAction(this::ebayButtonOnAction);
        this.bloggerButton.setOnAction(this::bloggerButtonOnAction);
        this.facebookButton.setOnAction(this::facebookButtonOnAction);
    }

    private void exitItemOnAction(ActionEvent event) {
        boolean res = exitDialog();

        if(res) System.exit(0);
    }

    private void amazonButtonOnAction(ActionEvent event) {
        if (this.amazonRadioMenuItem.isSelected()) {
            generateWebViewFor(AMAZON_URL);
        } else {
            selectionErrorDialog("Amazon");
        }
    }

    private void ebayButtonOnAction(ActionEvent event) {
        if (this.ebayRadioMenuItem.isSelected()) {
            generateWebViewFor(EBAY_URL);
        } else {
            selectionErrorDialog("Ebay");
        }
    }

    private void bloggerButtonOnAction(ActionEvent event) {
        String choice = selectBlogDialog();

        this.visitingLabel.setText("Visiting: " + choice);
    }

    private void facebookButtonOnAction(ActionEvent event) {
        String choice = enterUsernameDialog();

        this.visitingLabel.setText("Message sent as " + choice);
    }

    private void generateWebViewFor(String url) {
        WebView webView = new WebView();

        webView.getEngine().load(url);

        this.borderPane.setCenter(webView);
    }

    private boolean exitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Vas a salir del programa");
        alert.setContentText("¿Seguro que quieres salir?");

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void selectionErrorDialog(String name) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en la seleccion");
        alert.setHeaderText("No se puede comprar en " + name);
        alert.setContentText("Por favor, cambie la selección actual en el menu Opciones");

        alert.showAndWait();
    }

    private String selectBlogDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("El blog de Athos");
        choices.add("El blog de Porthos");
        choices.add("El blog de Aramis");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("El blog de Athos", choices);
        dialog.setTitle("Selecciona un blog");
        dialog.setHeaderText("¿Que blog quieres visitar?");
        dialog.setContentText("Elige:");

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    private String enterUsernameDialog() {
        TextInputDialog dialog = new TextInputDialog("pvtoari");
        dialog.setTitle("Introduce tu nombre");
        dialog.setHeaderText("¿Con que usuario quieres escribir en Facebook?");
        dialog.setContentText("Introduce tu nombre:");

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }
}
