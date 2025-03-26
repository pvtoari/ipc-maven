package net.pvtoari.ipcmaven.pract5.modalsIPC.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty surnames = new SimpleStringProperty();

    public Person(String name, String surnames) {
        this.name.setValue(name);
        this.surnames.setValue(surnames);
    }

    public final StringProperty nameProperty() {
        return this.name;
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public final StringProperty surnamesProperty() {
        return this.surnames;
    }

    public final String getSurnames() {
        return this.surnames.get();
    }

    public final void setSurnames(String surnames) {
        this.surnames.set(surnames);
    }
}
