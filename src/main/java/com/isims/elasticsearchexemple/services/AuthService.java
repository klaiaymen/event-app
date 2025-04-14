package com.isims.elasticsearchexemple.services;

import com.isims.elasticsearchexemple.dto.AuthRequest;
import com.isims.elasticsearchexemple.dto.AuthResponse;
import com.isims.elasticsearchexemple.dto.RegisterRequest;
import com.isims.elasticsearchexemple.entities.Role;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email déjà utilisé");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getPassword()));
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setRole(Role.PARTICIPANT);

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(request.getPassword(), user.getMotDePasse()))
            throw new RuntimeException("Mot de passe incorrect");

        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }
}
