package com.materiel.gestion.apigestion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.materiel.gestion.apigestion.exception.NoSuchEntityException;
import com.materiel.gestion.apigestion.model.dto.ErrorDto;

import io.jsonwebtoken.ExpiredJwtException;

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

		if (ex instanceof NoSuchEntityException || ex instanceof NoHandlerFoundException) {
			status = HttpStatus.NOT_FOUND;
		} else if (ex instanceof HttpMessageNotReadableException
				|| ex instanceof HttpRequestMethodNotSupportedException) {
			status = HttpStatus.BAD_REQUEST;
		} else if (ex instanceof ExpiredJwtException || ex instanceof AuthenticationException) {
			status = HttpStatus.UNAUTHORIZED;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return status;
	}

}
