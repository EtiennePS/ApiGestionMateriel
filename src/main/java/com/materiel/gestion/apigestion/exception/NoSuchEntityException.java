package com.materiel.gestion.apigestion.exception;

public class NoSuchEntityException extends RuntimeException {
	
	private static final long serialVersionUID = 1522790087051833978L;

	public NoSuchEntityException(String message, Exception e) {
		super(message, e);
	}
}
