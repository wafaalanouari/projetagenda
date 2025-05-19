package com.tonagenda.agenda1.repository;

import com.tonagenda.agenda1.model.Calendrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendrierRepository extends JpaRepository<Calendrier, Long> {
    // ... existing code ...
}