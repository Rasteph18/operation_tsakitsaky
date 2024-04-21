package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.PaiementBillet;

@Repository
public interface PaiementBilletRepository extends JpaRepository<PaiementBillet, Integer> {
    
}
