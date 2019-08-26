package com.materiel.gestion.apigestion.exception;

public class CreationException extends RuntimeException {
    public CreationException(String message, Exception e) {
        super(message, e);
    }
    public CreationException(String message) {
        super(message);
    }
}
