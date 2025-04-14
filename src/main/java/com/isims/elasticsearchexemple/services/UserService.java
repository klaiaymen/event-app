package com.isims.elasticsearchexemple.services;

import com.isims.elasticsearchexemple.entities.Role;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User addUser(User user) {
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return userRepository.save(user);
    }
    public User affecterRole(String idUser, Role role) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User non trouvé"));
        user.setRole(role);
        return userRepository.save(user);
    }

}
