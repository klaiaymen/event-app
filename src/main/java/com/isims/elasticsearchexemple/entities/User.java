package com.isims.elasticsearchexemple.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
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
