package com.isims.elasticsearchexemple.dto;

import com.isims.elasticsearchexemple.entities.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String adresse;
    private Role role;
}
