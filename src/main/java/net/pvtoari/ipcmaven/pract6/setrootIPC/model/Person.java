package net.pvtoari.ipcmaven.pract6.setrootIPC.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private final StringProperty nombre = new SimpleStringProperty();
	private final StringProperty apellidos = new SimpleStringProperty();
		
	public Person(String nombre, String apellidos)
	{
		this.nombre.setValue(nombre);
		this.apellidos.setValue(apellidos);
	}
	
	public  StringProperty NombreProperty() {
		return this.nombre;
	}
	public String getNombre() {
		return this.NombreProperty().get();
	}
	public final void setNombre(String Nombre) {
		this.NombreProperty().set(Nombre);
	}
	public  StringProperty ApellidosProperty() {
		return this.apellidos;
	}
	public String getApellidos() {
		return this.ApellidosProperty().get();
	}
	public  void setApellidos(String Apellidos) {
		this.ApellidosProperty().set(Apellidos);
	}

}