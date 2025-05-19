package com.tonagenda.agenda1.controller;

import com.tonagenda.agenda1.model.Utilisateur;
import com.tonagenda.agenda1.model.Evenement;
import com.tonagenda.agenda1.service.UtilisateurService;
import com.tonagenda.agenda1.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
public String showRegistrationForm(Model model) {
    model.addAttribute("utilisateur", new Utilisateur());
    return "register";
}

@PostMapping("/register")
public String registerUser(@ModelAttribute Utilisateur utilisateur) {
    utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
    utilisateur.setRole("USER");
    utilisateurService.saveUtilisateur(utilisateur);
    return "redirect:/login";
}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/evenements")
    public String listeEvenements(Model model, Principal principal) {
        List<Evenement> evenements = evenementService.getAllEvenements();
        model.addAttribute("evenements", evenements);
        model.addAttribute("username", principal != null ? principal.getName() : "");
        return "evenements";
    }
}