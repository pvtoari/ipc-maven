/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.pvtoari.ipcmaven.pract6.tableviewIPC.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.pvtoari.ipcmaven.pract6.tableviewIPC.model.Person;

public class PersonViewController implements Initializable {
    @FXML
    private TextField nameTextField, surnamesTextField;

    @FXML
    private Button acceptButton;

    @FXML
    private ComboBox<Image> imagesCombo;

    private Person person;
    private boolean acceptPressed = false;
    private ObservableList<Image> imagesList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagesList = FXCollections.observableArrayList(
                new Image(getClass().getResourceAsStream("/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Lloroso.png")),
                new Image(getClass().getResourceAsStream("/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Pregunta.png")),
                new Image(getClass().getResourceAsStream("/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Sonriente.png"))
        );

        imagesCombo.setCellFactory(_ -> new ImageComboBoxCell());
        imagesCombo.setButtonCell(new ImageComboBoxCell());

        imagesCombo.setItems(imagesList);
    }

    @FXML
    private void accept(ActionEvent event) {
        this.acceptPressed = true;

        if(!validateTextFields()) return;

        person.setName(nameTextField.getText());
        person.setSurname(surnamesTextField.getText());

        Image selectedImage = imagesCombo.getSelectionModel().getSelectedItem();

        switch (imagesList.indexOf(selectedImage)) {
            case 0 -> person.setImagePath(Person.LLOROSO_IMAGE_PATH);
            case 1 -> person.setImagePath(Person.PREGUNTA_IMAGE_PATH);
            case 2 -> person.setImagePath(Person.SONRIENTE_IMAGE_PATH);
        }

        closeStage();
    }

    @FXML
    private void cancel(ActionEvent event) {
        nameTextField.getScene().getWindow().hide();
    }

    public void initPerson(Person p) {
        person = p;
        nameTextField.setText(person.getName());
        surnamesTextField.setText(person.getSurname());

        switch (person.getImagePath()) {
            case Person.LLOROSO_IMAGE_PATH -> imagesCombo.getSelectionModel().select(0);
            case Person.PREGUNTA_IMAGE_PATH -> imagesCombo.getSelectionModel().select(1);
            case Person.SONRIENTE_IMAGE_PATH -> imagesCombo.getSelectionModel().select(2);
        }
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

    public boolean isAcceptPressed() {
        return acceptPressed;
    }

    public Person getPerson() {
        return person;
    }
}
