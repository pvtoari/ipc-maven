package net.pvtoari.ipcmaven.pract5.modalsIPC.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import net.pvtoari.ipcmaven.pract5.modalsIPC.controller.PersonListCell;
import net.pvtoari.ipcmaven.pract5.modalsIPC.model.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListViewController implements Initializable {
    @FXML
    private ListView<Person> peopleListView;

    @FXML
    private Button addButton, deleteButton, modifyButton;

    private ObservableList<Person> data = null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        peopleListView.setCellFactory(_ -> new PersonListCell());
        data = peopleListView.getItems();

        data.add(new Person("Pepe", "García"));
        data.add(new Person("María", "Pérez"));

        deleteButton.disableProperty().bind(Bindings.equal(peopleListView.getSelectionModel().selectedIndexProperty(), -1));
        modifyButton.disableProperty().bind(Bindings.equal(peopleListView.getSelectionModel().selectedIndexProperty(), -1));

        modifyButton.addEventHandler(ActionEvent.ACTION, this::openModifyModal);
        addButton.addEventHandler(ActionEvent.ACTION, this::openAddModal);
    }

    private void openAddModal(ActionEvent event) {
        PersonViewController modalController = triggerPersonViewModal(new Person("",""));

        if(modalController.isAcceptPressed()) data.add(modalController.getPerson());
    }

    private void openModifyModal(ActionEvent event) {
        PersonViewController modalController = triggerPersonViewModal();

        if(modalController.isAcceptPressed()) data.set(peopleListView.getSelectionModel().getSelectedIndex(), modalController.getPerson());
    }

    private PersonViewController triggerPersonViewModal() {
        return triggerPersonViewModal(peopleListView.getSelectionModel().getSelectedItem());
    }

    private PersonViewController triggerPersonViewModal(Person defaultPerson) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract5/modalsIPC/PersonView.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PersonViewController modalController = loader.getController();
        modalController.initPerson(defaultPerson);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("visualizar datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return modalController;
    }

    @FXML
    void handleDeleteButtonOnAction(ActionEvent event) {
        data.remove(peopleListView.getSelectionModel().getSelectedIndex());
    }
}

