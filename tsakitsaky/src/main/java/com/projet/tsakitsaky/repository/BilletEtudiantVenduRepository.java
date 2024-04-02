package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.BilletEtudiantVendu;

@Repository
public interface BilletEtudiantVenduRepository extends JpaRepository<BilletEtudiantVendu, Integer> {
    
}
