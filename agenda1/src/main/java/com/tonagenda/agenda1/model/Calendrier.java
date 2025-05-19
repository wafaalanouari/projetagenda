package com.tonagenda.agenda1.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Calendrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur proprietaire;

    @OneToMany(mappedBy = "calendrier", cascade = CascadeType.ALL)
    private Set<Evenement> evenements = new HashSet<>();

    // Getters et setters
    // ... existing code ...
}