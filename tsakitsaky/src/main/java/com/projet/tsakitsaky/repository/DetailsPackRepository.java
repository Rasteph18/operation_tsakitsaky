package com.projet.tsakitsaky.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projet.tsakitsaky.models.DetailsPack;

@Repository
public interface DetailsPackRepository extends JpaRepository<DetailsPack, Integer> {
    
    public List<DetailsPack> findByIdPack(int idPack);

    @Modifying
    @Query(
        value = "DELETE FROM details_pack WHERE id_pack = ?1",
        nativeQuery = true
    )
    public void deleteByIdPack(int idPack);
}
