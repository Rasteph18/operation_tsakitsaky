package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produit {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String nom;
    private String uniteMesure;

    public Produit()
    {

    }

    public Produit(int id, String nom, String uniteMesure)
    {
        setId(id);
        setNom(nom);
        setUniteMesure(uniteMesure);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getUniteMesure() {
        return uniteMesure;
    }
    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }
}
