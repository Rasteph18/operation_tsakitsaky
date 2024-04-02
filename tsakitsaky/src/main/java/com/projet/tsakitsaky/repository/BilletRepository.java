package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Billet;

@Repository
public interface BilletRepository extends JpaRepository<Billet, Integer> {
    
}
