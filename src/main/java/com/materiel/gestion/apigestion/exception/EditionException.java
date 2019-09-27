package com.materiel.gestion.apigestion.exception;

public class EditionException extends RuntimeException {
	
	private static final long serialVersionUID = 1080629234540705211L;
	
	public EditionException(String message, Exception e) {
        super(message, e);
    }
    public EditionException(String message) {
        super(message);
    }
}
