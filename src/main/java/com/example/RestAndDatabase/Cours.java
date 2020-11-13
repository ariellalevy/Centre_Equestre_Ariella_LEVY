package com.example.RestAndDatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String titre;
    private String dateCours;
    private String horaire;
    private long nbrCavalier;
    private int niveau;
    private String moniteur;
    private boolean is24before;
    private int compteurNbrCavalier;

    protected Cours() {}

    public Cours(String titre, String dateCours, String horaire, long nbrCavalier, int niveau, String moniteur, boolean is24before, int compteurNbrCavalier) {
        this.titre = titre;
        this.dateCours = dateCours;
        this.horaire = horaire;
        this.nbrCavalier = nbrCavalier;
        this.niveau = niveau;
        this.moniteur = moniteur;
        this.is24before = is24before;
        this.compteurNbrCavalier = compteurNbrCavalier;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, titre='%s', dateCours='%s', horaire='%s', nbrCavalier='%d', niveau='%d', idMoniteur='%s']",
                id, titre, dateCours, horaire, nbrCavalier, niveau, moniteur);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateCours(String dateCours) {
        this.dateCours = dateCours;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public void setNbrCavalier(long nbrCavalier) {
        this.nbrCavalier = nbrCavalier;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setMoniteur(String moniteur) {
        this.moniteur = moniteur;
    }

    public void setIs24before(boolean is24before) {
        this.is24before = is24before;
    }

    public void setCompteurNbrCavalier(int compteurNbrCavalier) {
        this.compteurNbrCavalier = compteurNbrCavalier;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDateCours() {
        return dateCours;
    }

    public String getHoraire() {
        return horaire;
    }

    public long getNbrCavalier() {
        return nbrCavalier;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getMoniteur() {
        return moniteur;
    }

    public boolean isIs24before() {
        return is24before;
    }

    public int getCompteurNbrCavalier() {
        return compteurNbrCavalier;
    }
}
