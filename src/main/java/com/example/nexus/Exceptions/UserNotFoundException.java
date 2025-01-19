package com.example.nexus.Exceptions;

// Déclarez chaque classe d'exception au niveau supérieur du package
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}