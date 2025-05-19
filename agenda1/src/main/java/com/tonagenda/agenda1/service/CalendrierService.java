package com.tonagenda.agenda1.service;

import com.tonagenda.agenda1.model.Calendrier;
import com.tonagenda.agenda1.repository.CalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendrierService {

    @Autowired
    private CalendrierRepository calendrierRepository;

    public List<Calendrier> getAllCalendriers() {
        return calendrierRepository.findAll();
    }

    public Optional<Calendrier> getCalendrierById(Long id) {
        return calendrierRepository.findById(id);
    }

    public Calendrier saveCalendrier(Calendrier calendrier) {
        return calendrierRepository.save(calendrier);
    }

    public void deleteCalendrier(Long id) {
        calendrierRepository.deleteById(id);
    }
}