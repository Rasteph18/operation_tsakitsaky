package com.projet.tsakitsaky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.Etudiant;
import com.projet.tsakitsaky.repository.EtudiantRepository;

@Service
public class EtudiantService {
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    

    public Etudiant getEtudiantById(String idEtudiant)
    {
        return etudiantRepository.findById(idEtudiant).get();
    }
}
