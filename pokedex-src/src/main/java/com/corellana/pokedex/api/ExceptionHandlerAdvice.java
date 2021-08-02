package com.corellana.pokedex.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.corellana.pokedex.exceptions.PokedexException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@Value("${api.message.error.400}")
	private String error400;

	@Value("${api.message.error.404}")
	private String error404;

	@Value("${api.message.error.500}")
	private String error500;

	@ExceptionHandler(PokedexException.class)
	public ResponseEntity<String> handleException(PokedexException e) {
		return ResponseEntity
				.status(e.getCode())
				.body(getErrorMessage(e.getCode()));
	} 

	private String getErrorMessage(int statusCode) {
		switch(statusCode) {
		case 400: return error400;
		case 404: return error404;
		default: return error500;
		}
	}
}