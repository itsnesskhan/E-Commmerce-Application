package com.ecom.response;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object data) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("Message", message);
		response.put("HttpStatus", httpStatus);
		response.put("Data", data);

		return new ResponseEntity<>(response, httpStatus);
	}

	public static ResponseEntity<Object> errorResponseBuilder(String message, HttpStatus httpStatus, Object error) {

		HashMap<String, Object> response = new HashMap<>();
		response.put("Message", message);
		response.put("HttpStatus", httpStatus);
		response.put("Error", error);

		return new ResponseEntity<>(response, httpStatus);
	}

}
