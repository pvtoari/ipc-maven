package net.pvtoari.ipcmaven.pract6.tableviewIPC.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	public static final String LLOROSO_IMAGE_PATH = "/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Lloroso.png";
	public static final String PREGUNTA_IMAGE_PATH = "/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Pregunta.png";
	public static final String SONRIENTE_IMAGE_PATH = "/net/pvtoari/ipcmaven/pract6/tableviewIPC/images/Sonriente.png";

	public static final Person EMPTY = new Person("", "", SONRIENTE_IMAGE_PATH);

	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty surnames = new SimpleStringProperty();
	private String imagePath;
		
	public Person(String nombre, String apellidos, String imagePath) {
		this.name.setValue(nombre);
		this.surnames.setValue(apellidos);
		this.imagePath = imagePath;
	}

	public Person(String nombre, String apellidos) {
		this(nombre, apellidos, SONRIENTE_IMAGE_PATH);
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}

	public String getName() {
		return this.nameProperty().get();
	}

	public final void setName(String Nombre) {
		this.nameProperty().set(Nombre);
	}

	public  StringProperty surnameProperty() {
		return this.surnames;
	}

	public String getSurname() {
		return this.surnameProperty().get();
	}

	public  void setSurname(String Apellidos) {
		this.surnameProperty().set(Apellidos);
	}

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}