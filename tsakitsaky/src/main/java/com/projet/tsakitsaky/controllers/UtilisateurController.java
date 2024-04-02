package com.projet.tsakitsaky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.tsakitsaky.models.Utilisateur;
import com.projet.tsakitsaky.services.UtilisateurService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UtilisateurController {
    
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String login()
    {
        return "login";
    }

    @PostMapping("/auth")
    public String Authentification(@RequestParam String mail, @RequestParam String mdp, HttpSession session)
    {
        Utilisateur user = utilisateurService.getUtilisateur(mail, mdp);

        if(user != null)
        {
            session.setAttribute("idUser", user.getId());
            session.setAttribute("etatUser", user.getSuperUtilisateur());

            return "accueil";
        }

        return "redirect:/?error=true";
    }
}
