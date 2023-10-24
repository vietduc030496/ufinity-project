package com.vti.ufinity.teaching.management.exception.advice;

import java.time.LocalDateTime;

import com.vti.ufinity.teaching.management.controller.auth.RegistrationController;
import com.vti.ufinity.teaching.management.exception.ApiExceptionResponse;
import com.vti.ufinity.teaching.management.exception.RegistrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@RestControllerAdvice(basePackageClasses = RegistrationController.class)
public class RegistrationControllerAdvice {

	@ExceptionHandler(RegistrationException.class)
	ResponseEntity<ApiExceptionResponse> handleRegistrationException(RegistrationException exception) {

		final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
