package com.isims.elasticsearchexemple.repositories;

import com.isims.elasticsearchexemple.entities.Evenement;
import com.isims.elasticsearchexemple.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EvenementRepository extends ElasticsearchRepository<Evenement, String> {
    List<Evenement> findByTitre(String titre);
    List<Evenement> findByCategorie(String categorie);
}
