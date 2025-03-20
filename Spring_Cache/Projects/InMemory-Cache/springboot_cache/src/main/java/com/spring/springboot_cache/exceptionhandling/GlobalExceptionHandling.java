package com.spring.springboot_cache.exceptionhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.ServletException;

@ControllerAdvice // for handling exceptions globally
public class GlobalExceptionHandling {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handlerPersonException(Exception ex){
		Map<String,String> error=new HashMap<>();
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(ServletException.class)
//	public ResponseEntity<Map<String,String>> handlerServletException(ServletException ex){
//		
//		Map<String,String> error=new HashMap<>();
//		error.put("message", ex.getMessage());
//		System.out.println("Entered..");
//		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//	}

}
