package application.admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Taula {

	private SimpleStringProperty nom;
	private SimpleIntegerProperty capacitat;

	public Taula(String nom, int capacitat) {
		this.nom = new SimpleStringProperty(nom);
		this.capacitat = new SimpleIntegerProperty(capacitat);
	}

	public int getCapacitat() {
		return capacitat.get();
	}

	public String getNom() {
		return nom.get();
	}

	public String toString() {
		return nom.get() + " Capacitat: " + capacitat.get() + " persones";
	}
}