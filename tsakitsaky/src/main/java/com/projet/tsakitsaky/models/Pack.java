package com.projet.tsakitsaky.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Pack {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private int id;
    private String nom;
    private int idBillet;
    private String photo;

    @Transient
    private Billet billet;
    @Transient
    private List<DetailsPack> detailsPack;


    public Pack() 
    {

    }


    public Pack(int id, String nom, int idBillet, String photo) 
    {
        setId(id);
        setNom(nom);
        setIdBillet(idBillet);
        setPhoto(photo);
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
    public int getIdBillet() {
        return idBillet;
    }
    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Billet getBillet() {
        return billet;
    }
    public void setBillet(Billet billet) {
        this.billet = billet;
    }
    public List<DetailsPack> getDetailsPack() {
        return detailsPack;
    }
    public void setDetailsPack(List<DetailsPack> detailsPack) {
        this.detailsPack = detailsPack;
    }

}
