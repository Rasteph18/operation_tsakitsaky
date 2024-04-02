package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "V_nb_type_billet_vendu")
public class VNbTypeBilletVendu {
    
    @Id
    private int id;
    private String idEtudiant;
    private String nom;
    private String prenom;
    private double prix;
    private double nbBillet;

    public VNbTypeBilletVendu()
    {
        
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIdEtudiant() {
        return idEtudiant;
    }
    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getNbBillet() {
        return nbBillet;
    }
    public void setNbBillet(double nbBillet) {
        this.nbBillet = nbBillet;
    }
}
