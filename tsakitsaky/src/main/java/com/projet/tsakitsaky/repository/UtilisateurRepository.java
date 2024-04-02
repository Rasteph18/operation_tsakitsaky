package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    
    @Query(
        value = "SELECT * FROM utilisateur WHERE mail LIKE ?1 AND mot_de_passe LIKE md5(?2)",
        nativeQuery = true
    )
    public Utilisateur getUtilisateur(String mail, String mdp);
}
