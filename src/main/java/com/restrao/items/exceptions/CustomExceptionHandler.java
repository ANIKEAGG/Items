package com.restrao.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ItemIdNotFound.class)
	public ResponseEntity<String> HandleItemIdNotFoundException(ItemIdNotFound e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
}
