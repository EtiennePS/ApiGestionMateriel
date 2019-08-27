package com.materiel.gestion.apigestion.exception;

public class EditionException extends RuntimeException {
	public EditionException(String message, Exception e) {
        super(message, e);
    }
    public EditionException(String message) {
        super(message);
    }
}
