package com.example.app1;

import java.sql.Time;
import java.util.Date;

public class Evenement {
    private int id;
    private String titre;
    private String lieu;
    private Date date ;
    private Time heure_debut;
    private Time heure_fin;
    public Evenement (){}
    public Evenement (String titre,String lieu){
        this.titre=titre;
        this.lieu=lieu;
    }
    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for titre
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    // Getter and Setter for lieu
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    // Getter and Setter for date
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Getter and Setter for heure_debut
    public Time getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heure_debut = heure_debut;
    }

    // Getter and Setter for heure_fin
    public Time getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(Time heure_fin) {
        this.heure_fin = heure_fin;
    }
}