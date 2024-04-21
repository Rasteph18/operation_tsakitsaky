package com.projet.tsakitsaky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.BilletEtudiantVendu;
import com.projet.tsakitsaky.repository.BilletEtudiantVenduRepository;

@Service
public class BilletEtudiantVenduService {
    
    @Autowired
    private BilletEtudiantVenduRepository billetEtudiantVenduRepository;

    @Autowired
    private BilletService billetService;

    public void insertBilletEtudiantVendu(BilletEtudiantVendu billetEtudiantVendu)
    {
        billetEtudiantVenduRepository.save(billetEtudiantVendu);
    }

    public double montantAPayerParEtudiant(String idEtudiant)
    {
        List<BilletEtudiantVendu> listBilletEtudiantVendus = billetEtudiantVenduRepository.findByIdEtudiant(idEtudiant);
        double montant = 0;
        for(int i = 0; i < listBilletEtudiantVendus.size(); i++)
        {
            double prixBillet = billetService.getBilletById(listBilletEtudiantVendus.get(i).getIdBillet()).getPrix();
            montant += (prixBillet*listBilletEtudiantVendus.get(i).getNbBillet());
        }

        return montant;
    }
}
