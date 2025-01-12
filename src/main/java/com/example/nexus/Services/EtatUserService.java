package com.example.nexus.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.EtatUser;
import com.example.nexus.Repository.EtatUserRepository;

@Service
public class EtatUserService {

    @Autowired
    private EtatUserRepository etatUserRepository;

    // Récupérer tous les états d'utilisateur
    public List<EtatUser> getAllEtatUser() {
        return etatUserRepository.findAll();
    }

    // Récupérer un état utilisateur par son ID
    public Optional<EtatUser> getEtatUserById(Long id) {
        return etatUserRepository.findById(id);
    }

    // Créer un nouvel état utilisateur
    public EtatUser createEtatUser(EtatUser etatUser) {
        return etatUserRepository.save(etatUser);
    }

    // Mettre à jour un état utilisateur existant
    public EtatUser updateEtatUser(Long id, EtatUser etatUserDetails) {
        Optional<EtatUser> etatUser = etatUserRepository.findById(id);
        if (etatUser.isPresent()) {
            EtatUser existingEtatUser = etatUser.get();
            existingEtatUser.setStatut(etatUserDetails.getStatut());
            existingEtatUser.setDescription(etatUserDetails.getDescription());
            existingEtatUser.setDateChangement(etatUserDetails.getDateChangement());
            existingEtatUser.setUser(etatUserDetails.getUser());
            return etatUserRepository.save(existingEtatUser);
        }
        return null; // Retourne null si l'état utilisateur n'existe pas
    }

    // Supprimer un état utilisateur
    public void deleteEtatUser(Long id) {
        etatUserRepository.deleteById(id);
    }
}
