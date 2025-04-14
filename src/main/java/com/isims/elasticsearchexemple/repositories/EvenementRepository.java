package com.isims.elasticsearchexemple.repositories;

import com.isims.elasticsearchexemple.entities.Evenement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EvenementRepository extends ElasticsearchRepository<Evenement, String> {
}
