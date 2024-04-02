package com.projet.tsakitsaky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.Billet;
import com.projet.tsakitsaky.models.VNbTypeBilletVendu;
import com.projet.tsakitsaky.repository.BilletRepository;
import com.projet.tsakitsaky.repository.VNbTypeBilletVenduRepository;

@Service
public class BilletService {
    
    @Autowired
    private BilletRepository billetRepository;

    @Autowired
    private VNbTypeBilletVenduRepository vNbTypeBilletVenduRepository;

    public List<Billet> getAllBillet()
    {
        return billetRepository.findAll();
    }

    //Le nombre et le type de billet vendu par étudiant
    public Page<VNbTypeBilletVendu> nbEtTypeBilletVendu(int numPage, int size)
    {
        Pageable pageable = PageRequest.of(numPage, size);
        return vNbTypeBilletVenduRepository.findAll(pageable);
    }
}
