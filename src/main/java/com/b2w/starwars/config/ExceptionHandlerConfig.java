package com.b2w.starwars.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.b2w.starwars.dto.ExceptionBody;
import com.b2w.starwars.exception.StarWarsException;

@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StarWarsException.class)
	ResponseEntity<Object> starWarsHandle(RuntimeException ex, WebRequest request) {
		ExceptionBody exceptionBody = new ExceptionBody();
		exceptionBody.setMessage(ex.getMessage());
		exceptionBody.setTime(LocalDateTime.now());
		return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
