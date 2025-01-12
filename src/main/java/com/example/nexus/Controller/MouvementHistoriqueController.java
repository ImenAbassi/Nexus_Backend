package com.example.nexus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.MouvementHistorique;
import com.example.nexus.Repository.MouvementHistoriqueRepository;

@RestController
@RequestMapping("/mouvements")
public class MouvementHistoriqueController {

     @Autowired
    private MouvementHistoriqueRepository mouvementHistoriqueRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MouvementHistorique>> getMouvementsByUser(@PathVariable Integer userId) {
        List<MouvementHistorique> mouvements = mouvementHistoriqueRepository.findByUser_IdUser(userId);
        
        if (mouvements.isEmpty()) {
            return ResponseEntity.noContent().build(); // Renvoie un 204 No Content si la liste est vide
        }
        
        return ResponseEntity.ok(mouvements); // Renvoie 200 OK avec la liste
    }
}
