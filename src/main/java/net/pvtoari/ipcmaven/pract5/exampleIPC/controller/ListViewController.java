package net.pvtoari.ipcmaven.pract5.exampleIPC.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;

import net.pvtoari.ipcmaven.pract5.exampleIPC.model.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListViewController implements Initializable {
    @FXML
    private ListView<Person> peopleListView;

    @FXML
    private TextField nameTextField, surnamesTextField;

    @FXML
    private Button addButton, deleteButton;

    private ObservableList<Person> data = null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        peopleListView.setCellFactory(a -> new PersonListCell());
        data = peopleListView.getItems();

        data.add(new Person("Pepe", "García"));
        data.add(new Person("María", "Pérez"));

        addButton.setDisable(true);

        deleteButton.disableProperty().bind(Bindings.equal(peopleListView.getSelectionModel().selectedIndexProperty(), -1));

        nameTextField.focusedProperty().addListener((a, b, c) -> {
            if (nameTextField.isFocused()) {
                addButton.setDisable(false);
                peopleListView.getSelectionModel().select(-1);
            }
        });

        peopleListView.focusedProperty().addListener((a, b, c) -> {
            if (peopleListView.isFocused()) {
                addButton.setDisable(true);
            }
        });
    }

    @FXML
    void addAction(ActionEvent event) {
        if (validateTextFields()) {
            data.add(new Person(nameTextField.getText(), surnamesTextField.getText()));
            nameTextField.clear();
            surnamesTextField.clear();

            nameTextField.requestFocus();
        }
    }

    private boolean validateTextFields() {
        return (!nameTextField.getText().isEmpty())
                && (!nameTextField.getText().trim().isEmpty())
                && (!surnamesTextField.getText().isEmpty())
                && (!surnamesTextField.getText().trim().isEmpty());
    }

    @FXML
    void removeAction(ActionEvent event) {
        data.remove(peopleListView.getSelectionModel().getSelectedIndex());
    }
}
