package com.materiel.gestion.apigestion.exception;

public class DeleteException extends RuntimeException {
    public DeleteException(String message, Exception e) {
        super(message, e);
    }
    public DeleteException(String message) {
        super(message);
    }
}
