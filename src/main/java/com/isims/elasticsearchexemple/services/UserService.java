package com.isims.elasticsearchexemple.services;

import com.isims.elasticsearchexemple.entities.Role;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User affecterRole(String idUser, Role role) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User non trouvé"));
        user.setRole(role);
        return userRepository.save(user);
    }
}
