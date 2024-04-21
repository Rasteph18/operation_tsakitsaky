package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaiementBillet {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String idEtudiant;
    private int etatPaiement;
    private double montant;

    
    public PaiementBillet() 
    {

    }

    public PaiementBillet(int id, String idEtudiant, int etatPaiement, double montant) throws Exception
    {
        setId(id);
        setIdEtudiant(idEtudiant);
        setEtatPaiement(etatPaiement);
        setMontant(montant);
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
    public int getEtatPaiement() {
        return etatPaiement;
    }
    public void setEtatPaiement(int etatPaiement) {
        this.etatPaiement = etatPaiement;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) throws Exception {
        if (montant < 0) {
            throw new Exception("Le montant doit etre positif");
        }
        this.montant = montant;
    }
}
