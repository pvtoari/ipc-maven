package net.pvtoari.ipcmaven.pract7.internationalizationIPC.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private final String AMAZON_URL = "https://www.amazon.es/";
    private final String EBAY_URL = "https://www.ebay.es/";

    private ResourceBundle bundle;

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
        this.bundle = rb;
        this.visitingLabel.setText(locale("visitingLabel.Visiting"));

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

        this.visitingLabel.setText(locale("visitingLabel.Visiting") + choice);
    }

    private void facebookButtonOnAction(ActionEvent event) {
        String choice = enterUsernameDialog();

        this.visitingLabel.setText(locale("visitingLabel.MessageSent") + choice);
    }

    private void generateWebViewFor(String url) {
        WebView webView = new WebView();

        webView.getEngine().load(url);

        this.borderPane.setCenter(webView);
    }

    private boolean exitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(locale("exitDialog.Title"));
        alert.setHeaderText(locale("exitDialog.Header"));
        alert.setContentText(locale("exitDialog.Content"));

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private void selectionErrorDialog(String name) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(locale("selectionErrorDialog.Title"));
        alert.setHeaderText(locale("selectionErrorDialog.Header") + name);
        alert.setContentText(locale("selectionErrorDialog.Content"));

        alert.showAndWait();
    }

    private String selectBlogDialog() {
        List<String> choices = new ArrayList<>();
        choices.add(locale("blogs.Athos"));
        choices.add(locale("blogs.Porthos"));
        choices.add(locale("blogs.Aramis"));

        ChoiceDialog<String> dialog = new ChoiceDialog<>(locale("blogs.Athos"), choices);
        dialog.setTitle(locale("selectBlogDialog.Title"));
        dialog.setHeaderText(locale("selectBlogDialog.Header"));
        dialog.setContentText(locale("selectBlogDialog.Content"));

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    private String enterUsernameDialog() {
        TextInputDialog dialog = new TextInputDialog("pvtoari");
        dialog.setTitle(locale("enterUsernameDialog.Title"));
        dialog.setHeaderText(locale("enterUsernameDialog.Header"));
        dialog.setContentText(locale("enterUsernameDialog.Content"));

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    private String locale(String s) {
        return bundle.getString(s);
    }
}
