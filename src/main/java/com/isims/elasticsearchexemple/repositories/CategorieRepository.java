package com.isims.elasticsearchexemple.repositories;

import com.isims.elasticsearchexemple.entities.Categorie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategorieRepository extends ElasticsearchRepository<Categorie, String> {

}
