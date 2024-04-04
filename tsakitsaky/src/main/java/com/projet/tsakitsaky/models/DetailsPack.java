package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class DetailsPack {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idPack;
    private int idProduit;
    private double quantite;

    @Transient
    private Produit produit;



    public DetailsPack()
    {

    }
    

    public DetailsPack(int id, int idPack, int idProduit, double quantite) throws Exception
    {
        setId(id);
        setIdPack(idPack);
        setIdProduit(idProduit);
        setQuantite(quantite);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdPack() {
        return idPack;
    }
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) throws Exception {
        if (quantite <= 0) {
            throw new Exception("La quantite doit etre positif");
        }
        this.quantite = quantite;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
