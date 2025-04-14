package com.isims.elasticsearchexemple.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
