package com.example.RestAndDatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Cours {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String titre;
    private Date dateCours;
    private String horaire;
    private long nbrCavalier;
    private int niveau;

    protected Cours() {}

    public Cours(String titre, Date dateCours, String horaire, long nbrCavalier, int niveau) {
        this.titre = titre;
        this.dateCours = dateCours;
        this.horaire = horaire;
        this.nbrCavalier = nbrCavalier;
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, titre='%s', dateCours='%d', horaire='%s', nbrCavalier='%d', niveau='%d']",
                id, titre, dateCours, horaire, nbrCavalier, niveau);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDateCours(Date dateCours) {
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

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Date getDateCours() {
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
}
