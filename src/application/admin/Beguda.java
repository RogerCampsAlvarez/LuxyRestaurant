package application.admin;

import javafx.beans.property.SimpleStringProperty;

public class Beguda {
    private SimpleStringProperty nom;
    private SimpleStringProperty taula;
    private SimpleStringProperty horaArrivada;

    public Beguda(String nom, String taula, String horaArrivada){
        this.nom = new SimpleStringProperty(nom);
        this.taula = new SimpleStringProperty(taula);
        this.horaArrivada = new SimpleStringProperty(horaArrivada);
    }

    public String getTaula() {
        return taula.get();
    }

    public String getNom() {
        return nom.get();
    }

    public String getHora() {
        return horaArrivada.get();
    }
}