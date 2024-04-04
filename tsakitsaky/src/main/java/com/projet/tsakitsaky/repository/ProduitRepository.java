package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    
}
