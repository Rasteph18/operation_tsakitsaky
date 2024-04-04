package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CoutRevientUnitaireProduit {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idProduit;
    private double prix;
    private double unite;

    public CoutRevientUnitaireProduit()
    {

    }

    

    public CoutRevientUnitaireProduit(int id, int idProduit, double prix, double unite) 
    {
        setId(id);
        setIdProduit(idProduit);
        setPrix(prix);
        setUnite(unite);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getUnite() {
        return unite;
    }
    public void setUnite(double unite) {
        this.unite = unite;
    }
}
