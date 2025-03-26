package net.pvtoari.ipcmaven.pract5.modalsIPC.controller;

import javafx.scene.control.ListCell;
import net.pvtoari.ipcmaven.pract5.modalsIPC.model.Person;

public class PersonListCell extends ListCell<Person> {
    @Override
    protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) setText(null);
        else setText(item.getName() + " ," + item.getSurnames());
    }
}
