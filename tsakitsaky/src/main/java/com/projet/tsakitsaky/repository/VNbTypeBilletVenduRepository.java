package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.VNbTypeBilletVendu;

@Repository
public interface VNbTypeBilletVenduRepository extends JpaRepository<VNbTypeBilletVendu, Integer> {
    
}
