package com.example.nexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.nexus.Entitie.ContactRH;
import com.example.nexus.Entitie.User;
import com.example.nexus.Repository.UserRepository;

@Service
public class ContactRHService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    public ContactRH envoyerMessageRH(Long userId, String objet, String description) {
        User utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Créer le message à envoyer
        String corpsEmail = utilisateur.getNom() + " " + utilisateur.getPrenom() + "\n" +
                            "Description:\n" + description;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("imen.abassi@nexuscontactcenter.com"); // Remplacez par l'adresse email du RH
        message.setSubject(objet);
        message.setText(corpsEmail);

        // Envoyer l'email
        mailSender.send(message);

        // Optionnel : créer un enregistrement ContactRH
        ContactRH contactRH = new ContactRH();
        contactRH.setObjet(objet);
        contactRH.setDescription(description);
        contactRH.setUser(utilisateur);
        // Enregistrez contactRH si nécessaire (avec le repository)

        return contactRH;
    }
}
