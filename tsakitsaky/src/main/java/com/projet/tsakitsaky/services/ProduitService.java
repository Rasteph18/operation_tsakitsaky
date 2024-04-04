package com.projet.tsakitsaky.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.Produit;
import com.projet.tsakitsaky.repository.ProduitRepository;

@Service
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduit()
    {
        return produitRepository.findAll();
    }

    public Produit getProduitById(int id)
    {
        return produitRepository.findById(id).get();
    }
}
