package com.isims.elasticsearchexemple.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document(indexName = "users")
public class User {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String adresse;
    private Role role;

}
