package com.isims.elasticsearchexemple.controllers;

import com.isims.elasticsearchexemple.entities.Evenement;
import com.isims.elasticsearchexemple.entities.Participant;
import com.isims.elasticsearchexemple.entities.Personnel;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.EvenementRepository;
import com.isims.elasticsearchexemple.services.EvenementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EvennementController {
    private final EvenementRepository evenementRepository;
    private final EvenementService evenementService;


    @PostMapping
    public Evenement saveEvent(@RequestBody Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @GetMapping
    public Iterable<Evenement> getAll() {
        return evenementRepository.findAll();
    }

    @GetMapping("/search")
    public List<Evenement> searchByTitle(@RequestParam String titre) {
        return evenementRepository.findByTitre(titre);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable String id) {
        if (evenementRepository.existsById(id)) {
            evenementRepository.deleteById(id);
            return "Evennement supprimé avec succès : " + id;
        } else {
            return "Evennement avec l'ID " + id + " introuvable.";
        }
    }

    @GetMapping("/{id}/participants")
    public List<Participant> getParticipants(@PathVariable String id) {
        return evenementService.getParticipantsByEvenementId(id);
    }

    @PostMapping("/{id}/inscription")
    public String inscrireParticipant(@PathVariable String id, @RequestBody Participant participant) {
        return evenementService.inscrireParticipant(id, participant);
    }

    @GetMapping("/{id}/personnels")
    public List<Personnel> getPersonnels(@PathVariable String id) {
        return evenementService.getPersonnelsByEvenementId(id);
    }

    @PostMapping("/{id}/affectation")
    public String affecterPersonnel(@PathVariable String id, @RequestBody Personnel personnel) {
        return evenementService.affecterPersonnel(id, personnel);
    }

    @PutMapping("/{evenementId}/desaffecter-personnel/{personnelId}")
    public ResponseEntity<String> desaffecterPersonnel(
            @PathVariable String evenementId,
            @PathVariable String personnelId
    ) {
        try {
            evenementService.desaffecterPersonnel(evenementId, personnelId);
            return ResponseEntity.ok("Personnel désaffecté avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{evenementId}/desinscrire-participant/{participantId}")
    public ResponseEntity<String> desinscrireParticipant(
            @PathVariable String evenementId,
            @PathVariable String participantId) {

        evenementService.desinscrireParticipant(evenementId, participantId);
        return ResponseEntity.ok("Participant désinscrit avec succès");
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Evenement>> getEvenementsByCategorie(@PathVariable String categorie) {
        List<Evenement> evenements = evenementService.getEvenementsByCategorie(categorie);
        return ResponseEntity.ok(evenements);
    }
}
