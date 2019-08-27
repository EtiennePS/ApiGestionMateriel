package com.materiel.gestion.apigestion.exception;

public class DataOwnerException extends RuntimeException {
    public DataOwnerException(String message, Exception e) {
        super(message, e);
    }
    public DataOwnerException(String message) {
        super(message);
    }
}
