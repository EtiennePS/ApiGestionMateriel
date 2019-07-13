package com.materiel.gestion.apigestion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.materiel.gestion.apigestion.exception.NoSuchEntityException;
import com.materiel.gestion.apigestion.model.dto.ErrorDto;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	private ResponseEntity<ErrorDto> handleException(Exception ex) {
		HttpStatus status = findHttpStatus(ex);
		ex.printStackTrace();
		ErrorDto e = new ErrorDto(status.value(), ex.getMessage());
		return ResponseEntity.status(status).body(e);
	}
	
	public static HttpStatus findHttpStatus(Exception ex) {
		HttpStatus status;
		
		if(ex instanceof NoSuchEntityException) {
			status = HttpStatus.NOT_FOUND;
		}
		else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return status;
	}

}
