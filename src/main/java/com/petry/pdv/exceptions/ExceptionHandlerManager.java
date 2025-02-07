package com.petry.pdv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.petry.pdv.utils.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerManager {
	
	@ExceptionHandler(AssinaturaAlreadyExistsException.class)
	public ResponseEntity assinaturaAlreadyExists(AssinaturaAlreadyExistsException e, WebRequest request) {
		return buildErrorResponse(e, "Assinatura já cadastrada para esse usuário!", HttpStatus.CONFLICT, request);
	}

	private ResponseEntity buildErrorResponse(AssinaturaAlreadyExistsException e, String message, HttpStatus conflict,
			WebRequest request) {
		return new ResponseEntity(new ErrorResponse(message), conflict);
	}
}
