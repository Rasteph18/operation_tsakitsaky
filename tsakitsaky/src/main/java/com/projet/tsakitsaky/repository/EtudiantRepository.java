package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    
}
