package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private String id;
    private String nom;
    private String prenom;
    private String sexe;

    public Etudiant()
    {

    }

    public Etudiant(String id, String nom, String prenom, String sexe)
    {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setSexe(sexe);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
}
