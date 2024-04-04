package com.projet.tsakitsaky.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.tsakitsaky.models.Billet;
import com.projet.tsakitsaky.models.DetailsPack;
import com.projet.tsakitsaky.models.Pack;
import com.projet.tsakitsaky.services.BilletService;
import com.projet.tsakitsaky.services.DetailsPackService;
import com.projet.tsakitsaky.services.PackService;
import com.projet.tsakitsaky.services.ProduitService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PackController {
    
    @Autowired
    private PackService packService;

    @Autowired
    private DetailsPackService detailsPackService;

    @Autowired
    private BilletService billetService;

    @Autowired
    private ProduitService produitService;

    @GetMapping("/pageInsertionPack")
    public String pageInsertionPack(@RequestParam(required = false) String message, Model model)
    {
        model.addAttribute("allBillet", billetService.getAllBillet());
        model.addAttribute("message", message);

        return "insertionPack";
    }

    @PostMapping("/insertionPack")
    public String insertionPack(@ModelAttribute Pack pack, HttpSession session)
    {
        List<DetailsPack> listDetailsPacks = new ArrayList<DetailsPack>();

        session.setAttribute("newPack", pack);
        session.setAttribute("listDetailsPack", listDetailsPacks);

        return "redirect:/pageInsertionDetailsPack";
    }

    @GetMapping("/pageInsertionDetailsPack")
    public String pageInsertionDetailsPack(@RequestParam(required = false) String message, Model model, HttpSession session)
    {
        Pack pack = (Pack)session.getAttribute("newPack");
        Billet billet = billetService.getBilletById(pack.getIdBillet());
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsPack");

        double prixTotalEntre = detailsPackService.prixTotal(listDetailsPacks);

        model.addAttribute("allProduit", produitService.getAllProduit());
        model.addAttribute("montant", billet.getPrix());
        model.addAttribute("listDetailsPack", listDetailsPacks);
        model.addAttribute("prixTotal", prixTotalEntre);
        model.addAttribute("message", message);

        return "insertionDetailsPack";
    }

    @PostMapping("/insertionDetailsPack")
    public String insertionDetailsPack(HttpServletRequest request, HttpSession session)
    {
        try {
            DetailsPack detailsPack = new DetailsPack();
          
            detailsPack.setIdProduit(Integer.parseInt(request.getParameter("idProduit")));
            detailsPack.setQuantite(Double.parseDouble(request.getParameter("quantite")));

            int idProduit = detailsPack.getIdProduit();
            detailsPack.setProduit(produitService.getProduitById(idProduit));
            List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsPack");

            listDetailsPacks.add(detailsPack);
            return "redirect:/pageInsertionDetailsPack";
        } catch (Exception e) {
            return "redirect:/pageInsertionDetailsPack?error=true&message="+e.getMessage();
        }
    }

    //ajout pack et details pack
    @GetMapping("/checkInsertPack")
    public String checkEtInsertionPack(HttpSession session) throws Exception
    {
        Pack pack = (Pack)session.getAttribute("newPack");
        Billet billet = billetService.getBilletById(pack.getIdBillet());
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsPack");

        double prixTotalEntre = detailsPackService.prixTotal(listDetailsPacks);
        double prixPack = billet.getPrix();

        String message = "";

        if(prixPack > prixTotalEntre) 
        {
            message = "Le prix Total des produits est inferieur au prix du pack";
            return "redirect:/pageInsertionDetailsPack?error=true&message="+message;
        } else if(prixPack < prixTotalEntre) {
            message = "Le prix Total des produits est superieur au prix du pack";
            return "redirect:/pageInsertionDetailsPack?error=true&message="+message;
        } 

        detailsPackService.insertPackEtDetailsPack(pack, listDetailsPacks);

        session.removeAttribute("newPack");
        session.removeAttribute("listDetailsPack");


        message = "Formule pack inserer avec success";
        return "redirect:/pageInsertionPack?success=true&message="+message;
    }

    @GetMapping("/cancelDetailsPack")
    public String annulerDetailsPack(HttpSession session)
    {
        List<DetailsPack> listDetailsPacks = new ArrayList<DetailsPack>();

        session.setAttribute("listDetailsPack", listDetailsPacks);

        return "redirect:/pageInsertionDetailsPack";
    }


    //liste des packs
    @GetMapping("/pageListePack")
    public String pageListePack(@RequestParam(required = false) String message,@RequestParam(defaultValue = "0") int numPage, Model model)
    {
        Page<Pack> listPack = packService.getAllPackWithBilletAndDetailsPack(numPage, 2);
        model.addAttribute("listePack", listPack.getContent());
        model.addAttribute("nbPage", listPack.getTotalPages());
        model.addAttribute("message", message);

        return "listePack";
    }


    //update formule pack
    @GetMapping("/pageUpdatePack")
    public String pageUpdatePack(@RequestParam int idPack, Model model, HttpSession session)
    {
        Pack pack = packService.getById(idPack);

        model.addAttribute("allBillet", billetService.getAllBillet());
        model.addAttribute("pack", pack);
        return "updateFormulePack_1";
    }

    @PostMapping("/updatePack")
    public String updatePack(@ModelAttribute Pack pack, HttpSession session)
    {
        session.setAttribute("pack", pack);
        session.setAttribute("listDetailsTemp", detailsPackService.getByIdPackWithProduit(pack.getId()));
        
        return "redirect:/pageUpdateFormulePack";
    }


    @GetMapping("/pageUpdateFormulePack")
    public String pageUpdateFormulePack(@RequestParam(required = false) String message, Model model, HttpSession session)
    {
        Pack pack = (Pack)session.getAttribute("pack");
        Billet billet = billetService.getBilletById(pack.getIdBillet());
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsTemp");

        
        double prixTotalEntre = detailsPackService.prixTotal(listDetailsPacks);

        model.addAttribute("allProduit", produitService.getAllProduit());
        model.addAttribute("montant", billet.getPrix());
        model.addAttribute("detailsPack", listDetailsPacks);
        model.addAttribute("prixTotal", prixTotalEntre);
        model.addAttribute("message", message);

        return "updateFormulePack_2";
    }

    @GetMapping("/removeProduit")
    public String removeProduit(@RequestParam int index, HttpSession session)
    {
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsTemp");
        listDetailsPacks.remove(index);

        return "redirect:/pageUpdateFormulePack";
    }

    @PostMapping("/modifDetailsPack")
    public String modifDetailsPack(@ModelAttribute DetailsPack detailsPack, HttpSession session)
    {
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsTemp");
        Pack pack = (Pack)session.getAttribute("pack");

        detailsPack.setIdPack(pack.getId());
        detailsPack.setProduit(produitService.getProduitById(detailsPack.getIdProduit()));
        listDetailsPacks.add(detailsPack);

        return "redirect:/pageUpdateFormulePack";
    }

    //ajout des modifications pack et details pack
    @GetMapping("/checkInsertUpdatePack")
    public String checkEtInsertionUpdatePack(HttpSession session) throws Exception
    {
        Pack pack = (Pack)session.getAttribute("pack");
        Billet billet = billetService.getBilletById(pack.getIdBillet());
        List<DetailsPack> listDetailsPacks = (List<DetailsPack>)session.getAttribute("listDetailsTemp");

        double prixTotalEntre = detailsPackService.prixTotal(listDetailsPacks);
        double prixPack = billet.getPrix();

        String message = "";

        if(prixPack > prixTotalEntre) 
        {
            message = "Le prix Total des produits est inferieur au prix du pack";
            return "redirect:/pageUpdateFormulePack?error=true&message="+message;
        } else if(prixPack < prixTotalEntre) {
            message = "Le prix Total des produits est superieur au prix du pack";
            return "redirect:/pageUpdateFormulePack?error=true&message="+message;
        } 

        detailsPackService.updatePackEtDetailsPack(pack, listDetailsPacks);

        session.removeAttribute("pack");
        session.removeAttribute("listDetailsTemp");


        message = "Formule pack modifier avec success";
        return "redirect:/pageListePack?success=true&message="+message;
    }

    @GetMapping("/cancelUpdateDetailsPack")
    public String annulerUpdateDetailsPack(HttpSession session)
    {
        Pack pack = (Pack)session.getAttribute("pack");
        session.setAttribute("listDetailsTemp", detailsPackService.getByIdPackWithProduit(pack.getId()));

        return "redirect:/pageUpdateFormulePack";
    }

    //supprimer un pack et ses details
    @GetMapping("/deletePack")
    public String supprimePack(@RequestParam int idPack)
    {
        detailsPackService.deletePackEtDetailsPackByIdPack(idPack);

        return "redirect:/pageListePack";
    }
}
