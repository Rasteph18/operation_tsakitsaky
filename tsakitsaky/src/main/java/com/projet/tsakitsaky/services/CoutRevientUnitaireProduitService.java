package com.projet.tsakitsaky.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.CoutRevientUnitaireProduit;
import com.projet.tsakitsaky.repository.CoutRevientUnitaireProduitRepository;

@Service
public class CoutRevientUnitaireProduitService {
    
    @Autowired
    private CoutRevientUnitaireProduitRepository coutRevientUnitaireProduitRepository;

    public CoutRevientUnitaireProduit getByIdProduit(int idProduit)
    {
        return coutRevientUnitaireProduitRepository.findByIdProduit(idProduit);
    }
}
