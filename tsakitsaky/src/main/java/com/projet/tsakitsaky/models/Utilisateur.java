package com.projet.tsakitsaky.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String mail;
    private String motDePasse;
    private int superUtilisateur;

    public Utilisateur()
    {

    }

    public Utilisateur(int id, String mail, String motDePasse, int superUtilisateur)
    {
        setId(id);
        setMail(mail);
        setMotDePasse(motDePasse);
        setSuperUtilisateur(superUtilisateur);
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public int getSuperUtilisateur() {
        return superUtilisateur;
    }
    public void setSuperUtilisateur(int superUtilisateur) {
        this.superUtilisateur = superUtilisateur;
    }
}
