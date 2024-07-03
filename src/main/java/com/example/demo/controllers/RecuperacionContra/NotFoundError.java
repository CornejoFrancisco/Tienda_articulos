package com.example.demo.controllers.RecuperacionContra;

public class NotFoundError extends RuntimeException {
    public NotFoundError(String message) {
        super(message);
    }
}