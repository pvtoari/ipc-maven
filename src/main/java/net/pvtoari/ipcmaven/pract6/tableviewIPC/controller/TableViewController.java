package net.pvtoari.ipcmaven.pract6.tableviewIPC.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Modality;
import javafx.stage.Stage;

import net.pvtoari.ipcmaven.pract6.tableviewIPC.model.Person;

public class TableViewController implements Initializable {
    private final ObservableList<?> datos = null;

    @FXML
    private Button addButton, modifyButton, deleteButton;

    @FXML
    private TableColumn<Person, String> nameColumn, surnameColumn, imageColumn;

    @FXML
    private TableView<Person> peopleTableView;

    private ObservableList<Person> myPeople;

    private void initializeModel() {
        myPeople = peopleTableView.getItems();

        ArrayList<Person> myData = new ArrayList<>();
        myData.add(new Person("Pepe", "García", Person.LLOROSO_IMAGE_PATH));
        myData.add(new Person("María", "Pérez"));

        myPeople.addAll(myData);
        peopleTableView.setItems(myPeople);

        nameColumn.setCellValueFactory((rowPerson) -> rowPerson.getValue().nameProperty());
        surnameColumn.setCellValueFactory((rowPerson) -> rowPerson.getValue().surnameProperty());
        imageColumn.setCellFactory(a -> new ImageTabCell());
        imageColumn.setCellValueFactory(rowPerson -> new SimpleStringProperty(rowPerson.getValue().getImagePath()));

        deleteButton.disableProperty().bind(Bindings.equal(peopleTableView.getSelectionModel().selectedIndexProperty(), -1));
        modifyButton.disableProperty().bind(Bindings.equal(peopleTableView.getSelectionModel().selectedIndexProperty(), -1));

        nameColumn.prefWidthProperty().bind(peopleTableView.widthProperty().multiply(0.3)); // 30% of table width
        surnameColumn.prefWidthProperty().bind(peopleTableView.widthProperty().multiply(0.4)); // 40% of table width
        imageColumn.prefWidthProperty().bind(peopleTableView.widthProperty().multiply(0.3)); // 30% of table width

        modifyButton.addEventHandler(ActionEvent.ACTION, this::openModifyModal);
        addButton.addEventHandler(ActionEvent.ACTION, this::openAddModal);
        deleteButton.addEventHandler(ActionEvent.ACTION, this::deleteAction);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeModel();
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int index = peopleTableView.getSelectionModel().getSelectedIndex();
        if(index != -1) peopleTableView.getItems().remove(index);
    }

    private void openAddModal(ActionEvent event) {
        PersonViewController modalController = triggerPersonViewModal(Person.EMPTY);

        if(modalController.isAcceptPressed()) {
            Person controllerPerson = modalController.getPerson();
            Person newPerson = new Person(
                    controllerPerson.getName(),
                    controllerPerson.getSurname(),
                    controllerPerson.getImagePath()
            );

            myPeople.add(newPerson);
            peopleTableView.refresh();
        }
    }

    private void openModifyModal(ActionEvent event) {
        PersonViewController modalController = triggerPersonViewModal();

        if(modalController.isAcceptPressed()) myPeople.set(peopleTableView.getSelectionModel().getSelectedIndex(), modalController.getPerson());
    }

    private PersonViewController triggerPersonViewModal() {
        return triggerPersonViewModal(peopleTableView.getSelectionModel().getSelectedItem());
    }

    private PersonViewController triggerPersonViewModal(Person defaultPerson) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/pvtoari/ipcmaven/pract6/tableviewIPC/view/PersonView.fxml"));
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
        stage.setTitle("visualizar datos persona 2: ahora es personal");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return modalController;
    }
}
