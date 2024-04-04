package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.CoutRevientUnitaireProduit;

@Repository
public interface CoutRevientUnitaireProduitRepository extends JpaRepository<CoutRevientUnitaireProduit, Integer> {
    
    public CoutRevientUnitaireProduit findByIdProduit(int idProduit);
}
