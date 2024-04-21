package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Axe;

@Repository
public interface AxeRepository extends JpaRepository<Axe, Integer> {
    
}
