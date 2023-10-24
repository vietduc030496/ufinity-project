package com.vti.ufinity.teaching.management.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
