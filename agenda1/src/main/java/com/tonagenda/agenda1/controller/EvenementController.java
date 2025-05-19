package com.tonagenda.agenda1.controller;

import com.tonagenda.agenda1.model.Calendrier;
import com.tonagenda.agenda1.model.Evenement;
import com.tonagenda.agenda1.repository.CalendrierRepository;
import com.tonagenda.agenda1.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @Autowired
    private CalendrierRepository calendrierRepository;

    @PostMapping("/from-calendar")
    public ResponseEntity<?> ajouterEvenementDepuisCalendrier(@RequestBody Map<String, String> payload) {
        try {
            String titre = payload.get("titre");
            String description = payload.get("description");
            String dateDebutStr = payload.get("dateDebut");
            String dateFinStr = payload.get("dateFin");
            String calendrierIdStr = payload.get("calendrierId");

            if (titre == null || description == null || dateDebutStr == null || dateFinStr == null || calendrierIdStr == null) {
                return ResponseEntity.badRequest().body("Paramètres manquants");
            }

            Long calendrierId = Long.parseLong(calendrierIdStr);
            Optional<Calendrier> calendrierOpt = calendrierRepository.findById(calendrierId);
            if (!calendrierOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Calendrier non trouvé");
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime dateDebut = LocalDate.parse(dateDebutStr, formatter).atStartOfDay();
            LocalDateTime dateFin = LocalDate.parse(dateFinStr, formatter).atStartOfDay();

            Evenement evt = new Evenement();
            evt.setTitre(titre);
            evt.setDescription(description);
            evt.setDateDebut(dateDebut);
            evt.setDateFin(dateFin);
            evt.setCalendrier(calendrierOpt.get());

            evenementService.saveEvenement(evt);
            return ResponseEntity.ok().body("Événement ajouté avec succès");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("calendrierId doit être un nombre");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur serveur : " + e.getMessage());
        }
    }
}