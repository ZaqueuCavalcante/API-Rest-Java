package br.com.zaqueucavalcante.apirestjava.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.zaqueucavalcante.apirestjava.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		Instant timestamp = Instant.now();
		Integer status = HttpStatus.NOT_FOUND.value();
		String error = "Resource not found.";
		String message = e.getMessage();
		String path = request.getRequestURI();
		StandardError standardError = new StandardError(timestamp, status, error, message, path);
		return ResponseEntity.status(status).body(standardError);
	}
}
