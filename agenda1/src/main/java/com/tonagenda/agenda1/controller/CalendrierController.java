package com.tonagenda.agenda1.controller;

import com.tonagenda.agenda1.model.Calendrier;
import com.tonagenda.agenda1.service.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/calendriers")
public class CalendrierController {

    @Autowired
    private CalendrierService calendrierService;

    @GetMapping
    public List<Calendrier> getAllCalendriers() {
        return calendrierService.getAllCalendriers();
    }

    @GetMapping("/{id}")
    public Optional<Calendrier> getCalendrierById(@PathVariable Long id) {
        return calendrierService.getCalendrierById(id);
    }

    @PostMapping
    public Calendrier createCalendrier(@RequestBody Calendrier calendrier) {
        return calendrierService.saveCalendrier(calendrier);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendrier(@PathVariable Long id) {
        calendrierService.deleteCalendrier(id);
    }
}