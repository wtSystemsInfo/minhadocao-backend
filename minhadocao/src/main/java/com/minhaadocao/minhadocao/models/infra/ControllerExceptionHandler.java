package com.minhaadocao.minhadocao.models.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.minhaadocao.minhadocao.models.exceptionDTO.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Já existe um resgistro com esses dados!", "400");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat404(EntityNotFoundException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Registro não localizado!", "404");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity threat404(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}
}
