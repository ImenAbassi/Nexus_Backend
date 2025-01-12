package com.example.nexus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Entitie.ContactRH;
import com.example.nexus.Services.ContactRHService;

@RestController
@RequestMapping("/contactRH")
public class ContactRHController {

    @Autowired
    private ContactRHService contactRHService;

    @PostMapping("/envoyer/{userId}")
    public ResponseEntity<ContactRH> envoyerMessageRH(
            @PathVariable Long userId,
            @RequestParam String objet,
            @RequestParam String description) {
        
        ContactRH contactRH = contactRHService.envoyerMessageRH(userId, objet, description);
        return ResponseEntity.ok(contactRH);
    }
}
