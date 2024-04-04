package com.projet.tsakitsaky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet.tsakitsaky.models.DetailsPack;
import com.projet.tsakitsaky.models.Pack;
import com.projet.tsakitsaky.repository.DetailsPackRepository;
import com.projet.tsakitsaky.repository.PackRepository;


@Service
public class PackService {
    
    @Autowired
    private PackRepository packRepository;

    @Autowired
    private BilletService billetService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private DetailsPackRepository detailsPackRepository;

    public Pack getById(int id)
    {
        return packRepository.findById(id).get();
    }

    public Pack insertPack(Pack pack)
    {
        return packRepository.save(pack);
    }

    public void deletePackById(int idPack)
    {
        packRepository.deleteById(idPack);
    }

    public Page<Pack> getAllPackWithBilletAndDetailsPack(int numeroPage, int size)
    {
        Pageable pageable = PageRequest.of(numeroPage, size);
        Page<Pack> listePack = packRepository.findAll(pageable);

        for(int i = 0; i < listePack.getContent().size(); i++)
        {
            listePack.getContent().get(i).setBillet(billetService.getBilletById(listePack.getContent().get(i).getIdBillet()));
            List<DetailsPack> listDetailsPacks = detailsPackRepository.findByIdPack(listePack.getContent().get(i).getId());
            for(int j = 0; j < listDetailsPacks.size(); j++)
            {
                listDetailsPacks.get(j).setProduit(produitService.getProduitById(listDetailsPacks.get(j).getIdProduit()));
            }
            listePack.getContent().get(i).setDetailsPack(listDetailsPacks);
        }

        return listePack;
    }
}
