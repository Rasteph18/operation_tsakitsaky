package com.projet.tsakitsaky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.tsakitsaky.models.BilletEtudiantVendu;
import com.projet.tsakitsaky.models.Etudiant;
import com.projet.tsakitsaky.models.PaiementBillet;
import com.projet.tsakitsaky.models.VNbTypeBilletVendu;
import com.projet.tsakitsaky.services.AxeService;
import com.projet.tsakitsaky.services.BilletEtudiantVenduService;
import com.projet.tsakitsaky.services.BilletService;
import com.projet.tsakitsaky.services.EtudiantService;
import com.projet.tsakitsaky.services.PaiementBilletService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BilletController {
    
    @Autowired
    private BilletEtudiantVenduService billetEtudiantVenduService;

    @Autowired
    private BilletService billetService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private PaiementBilletService paiementBilletService;

    @Autowired
    private AxeService axeService;

    @GetMapping("/pageInsertBilletEtudiantVendu")
    public String pageInsertBilletEtudiantVendu(Model model)
    {
        model.addAttribute("allBillet", billetService.getAllBillet());
        model.addAttribute("allAxe", axeService.getAllAxe());

        return "insertionBilletEtudiantVendu";
    }

    @PostMapping("/insertBilletEtudiantVendu")
    public String insertBilletEtudiantVendu(@ModelAttribute BilletEtudiantVendu billetEtudiantVendu)
    {
        billetEtudiantVenduService.insertBilletEtudiantVendu(billetEtudiantVendu);

        return "redirect:/pageInsertBilletEtudiantVendu";
    }

    @GetMapping("/pageNbEtTypeBilletVendu")
    public String pageNbEtTypeBilletVendu(Model model, @RequestParam(defaultValue = "0") int numPage)
    {
        Page<VNbTypeBilletVendu> p = billetService.nbEtTypeBilletVendu(numPage, 10);

        model.addAttribute("contenu", p.getContent());
        model.addAttribute("nbPage", p.getTotalPages());

        return "nbEtTypeBilletVendu";
    }

    //paiement des billets vendu
    @GetMapping("/pagePaiementBilletVendu")
    public String pagePaiementBilletVendu(@RequestParam(required = false) String idEtudiant, @RequestParam(required = false) String message, Model model)
    {
        double montantAPayer = 0;
        Etudiant etudiant = null;
        if(idEtudiant != null)
        {
            montantAPayer = billetEtudiantVenduService.montantAPayerParEtudiant(idEtudiant);
            etudiant = etudiantService.getEtudiantById(idEtudiant);
        }

        model.addAttribute("montantAPayer", montantAPayer);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("message", message);

        return "paiementBilletVendu";
    }

    @PostMapping("/insertPaiementVendu")
    public String insertPaiementVendu(HttpServletRequest request)
    {
        try {
            PaiementBillet paiementBillet = new PaiementBillet();

            String idEtudiant = request.getParameter("idEtudiant");
            double montant = Double.parseDouble(request.getParameter("montant"));

            paiementBillet.setIdEtudiant(idEtudiant);
            paiementBillet.setMontant(montant);

            double montantAPayer = billetEtudiantVenduService.montantAPayerParEtudiant(paiementBillet.getIdEtudiant());
            if (montantAPayer > paiementBillet.getMontant()) {
                paiementBillet.setEtatPaiement(20);
            } else if(montantAPayer == paiementBillet.getMontant()) {
                paiementBillet.setEtatPaiement(10);
            } else if(montantAPayer < paiementBillet.getMontant()) {
                throw new Exception("Le paiement doit etre inferieur au montant a payer");
            }
                
            paiementBilletService.insertPaiementBillet(paiementBillet);

            return "redirect:/pagePaiementBilletVendu?success=true&message=" + "Paiement effectuer avec success";
        } catch (Exception e) {
            return "redirect:/pagePaiementBilletVendu?error=true&message=" + e.getMessage();
        }
        
    }
}
