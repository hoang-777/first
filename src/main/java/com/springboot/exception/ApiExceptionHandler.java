package com.springboot.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(value= {ApiRequestException.class})
	public ResponseEntity <Object> handleApiRequestException(ApiRequestException e){

		HttpStatus badRequest = HttpStatus.NO_CONTENT;
		ApiException apiException = new ApiException(
				e.getMessage(),
				e,
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
				
				);
		
		return new ResponseEntity<>(apiException, badRequest);
		
		
		
	}
	
	@ExceptionHandler(value= {ApiRequestCorrect.class})
	public ResponseEntity <Object> handleApiRequestException(ApiRequestCorrect e){

		HttpStatus goodRequest = HttpStatus.CREATED;
		ApiException apiException = new ApiException(
				e.getMessage(),
				e,
				goodRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
				
				);
		
		return new ResponseEntity<>(apiException, goodRequest);
		
		
		
	}

}
