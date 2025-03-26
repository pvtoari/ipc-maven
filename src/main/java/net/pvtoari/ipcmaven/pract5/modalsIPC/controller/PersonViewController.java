package net.pvtoari.ipcmaven.pract5.modalsIPC.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import net.pvtoari.ipcmaven.pract5.modalsIPC.model.Person;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonViewController implements Initializable {
    @FXML
    private TextField nameTextField, surnamesTextField;

    private Person person;
    private boolean acceptPressed = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private void handleAcceptButtonOnAction(ActionEvent event) {
        this.acceptPressed = true;

        if(!validateTextFields()) return;

        person.setName(nameTextField.getText());
        person.setSurnames(surnamesTextField.getText());

        closeStage();
    }

    @FXML
    private void handleCancelButtonOnAction(ActionEvent event) {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        stage.close();
    }

    private boolean validateTextFields() {
        return (!nameTextField.getText().isEmpty())
                && (!nameTextField.getText().trim().isEmpty())
                && (!surnamesTextField.getText().isEmpty())
                && (!surnamesTextField.getText().trim().isEmpty());
    }

    public void initPerson(Person p) {
        person = p;
        nameTextField.setText(person.getName());
        surnamesTextField.setText(person.getSurnames());
    }

    public boolean isAcceptPressed() {
        return acceptPressed;
    }

    public Person getPerson() {
        return person;
    }
}
