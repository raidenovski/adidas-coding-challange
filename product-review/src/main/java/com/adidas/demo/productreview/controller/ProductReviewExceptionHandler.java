package com.adidas.demo.productreview.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ProductReviewExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFound(RuntimeException re, WebRequest request) {
		return handleExceptionInternal(re, "Could not find review with id " + re.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
}
