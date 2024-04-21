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
    private String nomClient;
    private String contact;
    private double nbBillet;
    private int idAxe;


    public BilletEtudiantVendu()
    {

    }

    public BilletEtudiantVendu(int id, int idBillet, String idEtudiant, String nomClient, String contact, double nbBillet, int idAxe)
    {
        setId(id);
        setIdBillet(idBillet);
        setIdEtudiant(idEtudiant);
        setNomClient(nomClient);
        setContact(contact);
        setNbBillet(nbBillet);
        setIdAxe(idAxe);
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
    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public int getIdAxe() {
        return idAxe;
    }
    public void setIdAxe(int idAxe) {
        this.idAxe = idAxe;
    }
}
