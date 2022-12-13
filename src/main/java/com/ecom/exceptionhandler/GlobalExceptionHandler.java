package com.ecom.exceptionhandler;

import static com.ecom.utills.Constants.ERROR_KEY;
import static com.ecom.utills.Messages.MOBILE_NUMBER_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.response.ResponseHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgsNotValidExceptionHandler(MethodArgumentNotValidException e) {
		HashMap<String, String> errors = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String errorMsg = error.getDefaultMessage();
			errors.put(field, errorMsg);
		});
		
		return ResponseHandler.errorResponseBuilder(MOBILE_NUMBER_EXCEPTION, HttpStatus.BAD_REQUEST, errors);
	}
	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> DataIntegerityExceptionHandler(DataIntegrityViolationException exception) {
		return new ResponseEntity<>(Map.entry(ERROR_KEY, exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> nullPointerExceptionHandler(NullPointerException exception) {
		return new ResponseEntity<>(Map.entry(ERROR_KEY, exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> runtimeExceptionHandler(RuntimeException exception) {
		System.out.println(exception);
		return new ResponseEntity<>(Map.entry(ERROR_KEY, exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
	

}
