package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BilletEtudiantVendu {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private int idBillet;
    private String idEtudiant;
    private double nbBillet;

    public BilletEtudiantVendu()
    {

    }

    public BilletEtudiantVendu(int id, int idBillet, String idEtudiant, double nbBillet)
    {
        setId(id);
        setIdBillet(idBillet);
        setIdEtudiant(idEtudiant);
        setNbBillet(nbBillet);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdBillet() {
        return idBillet;
    }
    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }
    public String getIdEtudiant() {
        return idEtudiant;
    }
    public void setIdEtudiant(String idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
    public double getNbBillet() {
        return nbBillet;
    }
    public void setNbBillet(double nbBillet) {
        this.nbBillet = nbBillet;
    }
}
