package com.logistic.courier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.logistic.courier.util.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ActiveStatusException.class)
	public ResponseEntity<ResponseStructure<String>> handleActiveStatusException(ActiveStatusException exception) {

	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    responseStructure.setMessage("Failed");
	    responseStructure.setData(exception.getMessage());

	    return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateResourceException(DuplicateResourceException exception) {

	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
	    responseStructure.setMessage("Failed");
	    responseStructure.setData(exception.getMessage());

	    return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidInputException(InvalidInputException exception) {

	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    responseStructure.setMessage("Failed");
	    responseStructure.setData(exception.getMessage());

	    return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidStatusException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidStatusException(InvalidStatusException exception) {

	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
	    responseStructure.setMessage("Failed");
	    responseStructure.setData(exception.getMessage());

	    return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleResourceNotFoundException(ResourceNotFoundException exception) {

	    ResponseStructure<String> responseStructure = new ResponseStructure<>();
	    responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
	    responseStructure.setMessage("Failed");
	    responseStructure.setData(exception.getMessage());

	    return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
	}
	

}
