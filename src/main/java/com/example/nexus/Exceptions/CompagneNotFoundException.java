package com.example.nexus.Exceptions;

public class CompagneNotFoundException extends RuntimeException {
    public CompagneNotFoundException(String message) {
        super(message);
    }
}