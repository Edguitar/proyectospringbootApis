package com.curso.java.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.curso.java.dtos.ErrorResponse;

public class BaseController {

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResponse return409(DataIntegrityViolationException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
	}
	
}
