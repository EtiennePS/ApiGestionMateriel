package com.materiel.gestion.apigestion.exception;

public class DeleteException extends RuntimeException {
	
	private static final long serialVersionUID = 801445113870946029L;
	
	public DeleteException(String message, Exception e) {
        super(message, e);
    }
    public DeleteException(String message) {
        super(message);
    }
}
