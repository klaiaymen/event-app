package com.isims.elasticsearchexemple.repositories;

import com.isims.elasticsearchexemple.entities.Role;
import com.isims.elasticsearchexemple.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    List<User> findByNom(String nom);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findUserByRole(Role role);
}
