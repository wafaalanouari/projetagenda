package com.tonagenda.agenda1.repository;

import com.tonagenda.agenda1.model.Utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // ... existing code ...
    Optional<Utilisateur> findByEmail(String email);
}