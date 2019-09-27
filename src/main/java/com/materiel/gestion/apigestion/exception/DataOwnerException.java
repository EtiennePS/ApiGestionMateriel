package com.materiel.gestion.apigestion.exception;

public class DataOwnerException extends RuntimeException {
	
	private static final long serialVersionUID = -5039046723075182823L;
	
	public DataOwnerException(String message, Exception e) {
        super(message, e);
    }
    public DataOwnerException(String message) {
        super(message);
    }
}
