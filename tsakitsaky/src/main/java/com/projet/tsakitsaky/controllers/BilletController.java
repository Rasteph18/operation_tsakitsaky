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
import com.projet.tsakitsaky.models.VNbTypeBilletVendu;
import com.projet.tsakitsaky.services.BilletEtudiantVenduService;
import com.projet.tsakitsaky.services.BilletService;

@Controller
public class BilletController {
    
    @Autowired
    private BilletEtudiantVenduService billetEtudiantVenduService;

    @Autowired
    private BilletService billetService;

    @GetMapping("/pageInsertBilletEtudiantVendu")
    public String pageInsertBilletEtudiantVendu(Model model)
    {
        model.addAttribute("allBillet", billetService.getAllBillet());

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
}
