package net.pvtoari.ipcmaven.pract8.exampleIPC.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private VBox vBox;

    @FXML
    private Button button1, button2, button3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String styles =
                "-fx-background-color: #444349;" +
                        "-fx-border-color: #ff0000;" +
                        "-fx-text-fill: #dddddd;";

        button3.setStyle(styles);

        String css = getClass().getResource("/net/pvtoari/ipcmaven/pract8/exampleIPC/styles/button-styles-2.css").toExternalForm();
        button1.getStylesheets().add(css);

        button2.setOnMouseEntered(ae -> button2.setStyle("-fx-font-size: 15px"));
        button2.setOnMouseExited(ae -> button2.setStyle(""));
    }
}
