package net.pvtoari.ipcmaven.pract5.exampleIPC.controller;

import net.pvtoari.ipcmaven.pract5.exampleIPC.model.Person;

import javafx.scene.control.ListCell;

public class PersonListCell extends ListCell<Person> {
    @Override
    protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) setText(null);
        else setText(item.getName() + " ," + item.getSurnames());
    }
}
