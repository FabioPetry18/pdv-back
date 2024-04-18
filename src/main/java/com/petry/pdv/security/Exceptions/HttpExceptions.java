package com.petry.pdv.security.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petry.pdv.utils.ErrorResponse;

@ControllerAdvice
@RestController
public class HttpExceptions {

	
	   @ResponseStatus(HttpStatus.FORBIDDEN)
	   @ExceptionHandler({AccessDeniedException.class})
	    public ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
	        return new ErrorResponse("Acesso negado: " + ex.getMessage());
	    }
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleException(Exception ex) {
	        Map<String, Object> body = new HashMap<>();
	        body.put("message", "Ocorreu um erro inesperado no sistema!");

	        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
}
