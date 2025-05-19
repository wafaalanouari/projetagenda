package com.tonagenda.agenda1.repository;

import com.tonagenda.agenda1.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}