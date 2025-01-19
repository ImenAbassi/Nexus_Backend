package com.example.nexus.Exceptions;

public class UserCompagneNotFoundException extends RuntimeException {
    public UserCompagneNotFoundException(String message) {
        super(message);
    }
}