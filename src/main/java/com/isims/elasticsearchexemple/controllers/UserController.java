package com.isims.elasticsearchexemple.controllers;

import com.isims.elasticsearchexemple.entities.Role;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.UserRepository;
import com.isims.elasticsearchexemple.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/search")
    public List<User> searchByNom(@RequestParam String nom) {
        return userRepository.findByNom(nom);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "Utilisateur supprimé avec succès : " + id;
        } else {
            return "Utilisateur avec l'ID " + id + " introuvable.";
        }
    }


    @PutMapping("/{id}/role")
    public ResponseEntity<User> affecterRole(
            @PathVariable String id,
            @RequestParam Role role) {
        return ResponseEntity.ok(userService.affecterRole(id, role));
    }
}
