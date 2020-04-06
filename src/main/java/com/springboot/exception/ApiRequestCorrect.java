package com.springboot.exception;

public class ApiRequestCorrect extends RuntimeException{
	
	public ApiRequestCorrect(String message) {
		super(message);
		
	}
	
	public ApiRequestCorrect(String message, Throwable cause) {
		super(message,cause);
	}

}
