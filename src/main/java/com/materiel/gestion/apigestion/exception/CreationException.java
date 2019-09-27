package com.materiel.gestion.apigestion.exception;

public class CreationException extends RuntimeException {
    
	private static final long serialVersionUID = -5497407979723446896L;
	
	public CreationException(String message, Exception e) {
        super(message, e);
    }
    public CreationException(String message) {
        super(message);
    }
}
