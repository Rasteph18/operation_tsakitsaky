package com.projet.tsakitsaky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {
    
}
