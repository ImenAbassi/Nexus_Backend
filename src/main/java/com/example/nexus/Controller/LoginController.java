package com.example.nexus.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nexus.Services.AuthService;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")  // Ajouter CORS ici aussi
public class LoginController {

    @Autowired
    private AuthService authService;  // Injecter le service d'authentification

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String cin, @RequestParam String password) {
        boolean isAuthenticated = authService.authenticate(cin, password);

        if (isAuthenticated) {
            // Retourner une réponse OK avec le message de succès
            return ResponseEntity.ok("Authentification réussie");
        } else {
            // Retourner une réponse Unauthorized (401) avec le message d'erreur
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
        }
    }
@PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Invalidates the authentication session
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Déconnexion réussie !");
    }


    
}
