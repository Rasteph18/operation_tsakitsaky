package com.projet.tsakitsaky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.PaiementBillet;
import com.projet.tsakitsaky.repository.PaiementBilletRepository;

@Service
public class PaiementBilletService {
    
    @Autowired
    private PaiementBilletRepository paiementBilletRepository;

    public void insertPaiementBillet(PaiementBillet paiementBillet)
    {
        paiementBilletRepository.save(paiementBillet);
    }
}
