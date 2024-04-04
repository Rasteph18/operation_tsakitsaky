package com.projet.tsakitsaky.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projet.tsakitsaky.models.CoutRevientUnitaireProduit;
import com.projet.tsakitsaky.models.DetailsPack;
import com.projet.tsakitsaky.models.Pack;
import com.projet.tsakitsaky.repository.DetailsPackRepository;

@Service
public class DetailsPackService {
    
    @Autowired
    private DetailsPackRepository detailsPackRepository;

    @Autowired
    private PackService packService;

    @Autowired
    private CoutRevientUnitaireProduitService coutRevientUnitaireProduitService;

    @Autowired
    private ProduitService produitService;

    public void insertDetailsPack(DetailsPack detailsPack)
    {
        detailsPackRepository.save(detailsPack);
    }

    public double prixTotal(List<DetailsPack> listDetailsPacks)
    {
        double prixTotal = 0;
        double quantiteEntre = 0;

        CoutRevientUnitaireProduit coutRevient = null;

        for(int i = 0; i < listDetailsPacks.size(); i++)
        {
            quantiteEntre = listDetailsPacks.get(i).getQuantite();
            coutRevient = coutRevientUnitaireProduitService.getByIdProduit(listDetailsPacks.get(i).getIdProduit());
            prixTotal += (coutRevient.getPrix() * quantiteEntre) / coutRevient.getUnite();
        }

        return prixTotal;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void insertPackEtDetailsPack(Pack pack, List<DetailsPack> listDetailsPacks)
    {
        Pack newPack = packService.insertPack(pack);
        int idNewPack = newPack.getId();

        DetailsPack detailsPack = null;

        for(int i = 0; i < listDetailsPacks.size(); i++)
        {
            detailsPack = listDetailsPacks.get(i);
            detailsPack.setIdPack(idNewPack);

            detailsPackRepository.save(detailsPack);
        }
    }

    public List<DetailsPack> getByIdPack(int idPack)
    {
        return detailsPackRepository.findByIdPack(idPack);
    }

    public List<DetailsPack> getByIdPackWithProduit(int idPack)
    {
        List<DetailsPack> listDetailsPacks = getByIdPack(idPack);

        for(int i = 0; i < listDetailsPacks.size(); i++)
        {
            listDetailsPacks.get(i).setProduit(produitService.getProduitById(listDetailsPacks.get(i).getIdProduit()));
        }

        return listDetailsPacks;
    }

    public void deleteByIdPack(int idPack)
    {
        detailsPackRepository.deleteByIdPack(idPack);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public void updatePackEtDetailsPack(Pack pack, List<DetailsPack> listDetailsPacks)
    {
        packService.insertPack(pack);
        deleteByIdPack(pack.getId());

        for(int i = 0; i < listDetailsPacks.size(); i++)
        {
            insertDetailsPack(listDetailsPacks.get(i));
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deletePackEtDetailsPackByIdPack(int idPack)
    {
        deleteByIdPack(idPack);
        packService.deletePackById(idPack);
    }
}
