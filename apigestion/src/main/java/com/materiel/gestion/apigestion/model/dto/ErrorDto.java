package com.materiel.gestion.apigestion.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorDto {
	
	private int httpCode;
	private String message;
	
	public ErrorDto(int httpCode, String message) {
		this.httpCode = httpCode;
		this.message = message;
	}
	
}
