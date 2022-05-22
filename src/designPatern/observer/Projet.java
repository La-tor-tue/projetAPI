package designPatern.observer;

import java.time.LocalDate;

public class Projet extends Subject {
    protected String nom;
    protected LocalDate dateFin;

    public Projet(String nom, LocalDate dateFin) {
        this.nom = nom;
        this.dateFin = dateFin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        notifyObservers();
    }

    @Override
    public String getNotification() {
        return "Nouvelle date " + nom + " " + dateFin;
    }
}
