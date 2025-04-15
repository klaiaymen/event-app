package com.isims.elasticsearchexemple.controllers;

import com.isims.elasticsearchexemple.entities.Categorie;
import com.isims.elasticsearchexemple.entities.User;
import com.isims.elasticsearchexemple.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategorieController {
    @Autowired
    private CategorieRepository categorieRepository;

    @PostMapping
    public Categorie saveCategorie(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @GetMapping
    public Iterable<Categorie> getAll() {
        return categorieRepository.findAll();
    }


    @DeleteMapping("/{id}")
    public String deleteCategorie(@PathVariable String id) {
        if (categorieRepository.existsById(id)) {
            categorieRepository.deleteById(id);
            return "categorie supprimé avec succès : " + id;
        } else {
            return "categorie avec l'ID " + id + " introuvable.";
        }
    }

}
