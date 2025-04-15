package com.isims.elasticsearchexemple.services;


import com.isims.elasticsearchexemple.entities.Evenement;
import com.isims.elasticsearchexemple.entities.Participant;
import com.isims.elasticsearchexemple.entities.Personnel;
import com.isims.elasticsearchexemple.repositories.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {
    @Autowired
    private EvenementRepository evenementRepository;

    public List<Participant> getParticipantsByEvenementId(String evenementId) {
        return evenementRepository.findById(evenementId)
                .map(Evenement::getParticipants)
                .orElse(null);
    }

    public List<Personnel> getPersonnelsByEvenementId(String evenementId) {
        return evenementRepository.findById(evenementId)
                .map(Evenement::getPersonnels)
                .orElse(null);
    }

    public String inscrireParticipant(String evenementId, Participant participant) {
        return evenementRepository.findById(evenementId).map(evenement -> {

            List<Participant> participants = evenement.getParticipants();
            if (participants == null) {
                participants = new ArrayList<>();
            }

            // Vérifier s'il est déjà inscrit
            boolean dejaInscrit = participants.stream()
                    .anyMatch(p -> p.getEmail().equalsIgnoreCase(participant.getEmail()));

            if (dejaInscrit) {
                return "Participant déjà inscrit à cet événement.";
            }

            // Vérifier la limite maximale de participants
            if (participants.size() >= evenement.getNbMaxParticipants()) {
                return "Limite de participants atteinte.";
            }

            participants.add(participant);
            evenement.setParticipants(participants);
            evenementRepository.save(evenement);

            return "Inscription réussie !";
        }).orElse("Événement non trouvé.");
    }

    public String affecterPersonnel(String evenementId, Personnel personnel) {
        return evenementRepository.findById(evenementId).map(evenement -> {

            List<Personnel> personnels = evenement.getPersonnels();
            if (personnels == null) {
                personnels = new ArrayList<>();
            }

            // Vérifier s'il est déjà inscrit
            boolean dejaInscrit = personnels.stream()
                    .anyMatch(p -> p.getEmail().equalsIgnoreCase(personnel.getEmail()));

            if (dejaInscrit) {
                return "Personnel déjà affecté à cet événement.";
            }

            personnels.add(personnel);
            evenement.setPersonnels(personnels);
            evenementRepository.save(evenement);

            return "affectation réussie !";
        }).orElse("Événement non trouvé.");
    }

    public void desaffecterPersonnel(String evenementId, String personnelId) {
        Optional<Evenement> optionalEvenement = evenementRepository.findById(evenementId);
        if (optionalEvenement.isPresent()) {
            Evenement evenement = optionalEvenement.get();

            List<Personnel> personnels = evenement.getPersonnels();
            personnels.removeIf(p -> p.getId().equals(personnelId));

            evenement.setPersonnels(personnels);
            evenementRepository.save(evenement);
        } else {
            throw new RuntimeException("Événement non trouvé !");
        }
    }

    public void desinscrireParticipant(String evenementId, String participantId) {
        Evenement evenement = evenementRepository.findById(evenementId)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé"));

        List<Participant> participants = evenement.getParticipants();

        boolean removed = participants.removeIf(p -> p.getId().equals(participantId));

        if (!removed) {
            throw new RuntimeException("Participant non trouvé dans l'événement");
        }

        evenement.setParticipants(participants);
        evenementRepository.save(evenement);
    }

    public List<Evenement> getEvenementsByCategorie(String categorie) {
        return evenementRepository.findByCategorie(categorie);
    }
}
