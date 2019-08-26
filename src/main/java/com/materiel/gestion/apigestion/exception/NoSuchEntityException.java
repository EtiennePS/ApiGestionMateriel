package com.materiel.gestion.apigestion.exception;

public class NoSuchEntityException extends RuntimeException {

	public NoSuchEntityException(String message, Exception e) {
		super(message, e);
	}
}
