package com.projet.tsakitsaky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.Utilisateur;
import com.projet.tsakitsaky.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur getUtilisateur(String mail, String mdp)
    {
        return utilisateurRepository.getUtilisateur(mail, mdp);
    }
}
