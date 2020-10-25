package com.example.RestAndDatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cour {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private int idCour;
    private String moniteur;
    private String cavalier;
    private String cheval;

    protected Cour(){}

    public Cour(int idCours, String moniteur, String cavalier, String cheval) {
        this.idCour = idCours;
        this.moniteur = moniteur;
        this.cavalier = cavalier;
        this.cheval = cheval;
    }

    public Long getId() {
        return id;
    }

    public int getIdCour() {
        return idCour;
    }

    public String getMoniteur() {
        return moniteur;
    }

    public String getCavalier() {
        return cavalier;
    }

    public String getCheval() {
        return cheval;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdCour(int idCour) {
        this.idCour = idCour;
    }

    public void setMoniteur(String moniteur) {
        this.moniteur = moniteur;
    }

    public void setCavalier(String cavalier) {
        this.cavalier = cavalier;
    }

    public void setCheval(String cheval) {
        this.cheval = cheval;
    }
}
