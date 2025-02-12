package com.example.nexus.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nexus.Entitie.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @SuppressWarnings("null")
    Optional<User> findById(Long userId);

    Optional<User> findByAdresseMail(String adresseMail); // Ajout de la méthode pour rechercher par adresse e-mail

    Optional<User> findByCin(String cin); // Méthode pour trouver un utilisateur par CIN
    /* Optional<User> findById(User userId); */

    Optional<User> findByNom(String nom);
}
