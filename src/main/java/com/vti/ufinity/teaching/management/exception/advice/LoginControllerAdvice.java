package com.vti.ufinity.teaching.management.exception.advice;

import java.time.LocalDateTime;

import com.vti.ufinity.teaching.management.controller.auth.LoginController;
import com.vti.ufinity.teaching.management.exception.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@RestControllerAdvice(basePackageClasses = LoginController.class)
public class LoginControllerAdvice {

	@ExceptionHandler(BadCredentialsException.class)
	ResponseEntity<ApiExceptionResponse> handleRegistrationException(BadCredentialsException exception) {

		final ApiExceptionResponse response = new ApiExceptionResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
