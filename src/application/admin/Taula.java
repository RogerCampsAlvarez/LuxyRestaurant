package application.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Taula {
	//int id;
	String nom;
	int capacitat;
	
	public Taula(String nom, int capacitat) {
		//this.id = id;
		this.nom = nom;
		this.capacitat = capacitat;
	}
	
	private StringProperty spNom;

	public void setFirstName(String value) {
		firstNameProperty().set(value);
	}

	public String getFirstName() {
		return firstNameProperty().get();
	}

	public StringProperty firstNameProperty() {
		if (spNom == null)
			spNom = new SimpleStringProperty(this, "nom");
		return spNom;
	}

	private StringProperty spCapacitat;

	public void setLastName(String value) {
		lastNameProperty().set(value);
	}

	public String getLastName() {
		return lastNameProperty().get();
	}

	public StringProperty lastNameProperty() {
		if (spCapacitat == null)
			spCapacitat = new SimpleStringProperty(this, "capacitat");
		return spCapacitat;
	}

	public String toString() {
		return nom+" Capacitat: "+capacitat+" persones";
	}
}