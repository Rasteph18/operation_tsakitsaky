package com.projet.tsakitsaky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.BilletEtudiantVendu;

@Repository
public interface BilletEtudiantVenduRepository extends JpaRepository<BilletEtudiantVendu, Integer> {
    
    public List<BilletEtudiantVendu> findByIdEtudiant(String idEtudiant);
}
