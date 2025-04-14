package com.isims.elasticsearchexemple.repositories;

import com.isims.elasticsearchexemple.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByNom(String nom);
}
