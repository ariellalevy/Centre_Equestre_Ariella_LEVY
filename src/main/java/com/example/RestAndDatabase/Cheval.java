package com.example.RestAndDatabase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cheval {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nom;
    private String type;
    private double poids;
    private double taille;

    protected Cheval() {}

    public Cheval(String nom, String type, double poids, double taille) {
        this.nom = nom;
        this.type = type;
        this.poids = poids;
        this.taille = taille;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, nom='%s', type='%s', poids='%d', taille='%d']",
                id, nom, type, poids, taille);
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public double getPoids() {
        return poids;
    }

    public double getTaille() {
        return taille;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }
}