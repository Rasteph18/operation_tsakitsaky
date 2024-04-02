package com.projet.tsakitsaky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.BilletEtudiantVendu;
import com.projet.tsakitsaky.repository.BilletEtudiantVenduRepository;

@Service
public class BilletEtudiantVenduService {
    
    @Autowired
    private BilletEtudiantVenduRepository billetEtudiantVenduRepository;

    public void insertBilletEtudiantVendu(BilletEtudiantVendu billetEtudiantVendu)
    {
        billetEtudiantVenduRepository.save(billetEtudiantVendu);
    }
}
