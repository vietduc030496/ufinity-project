package com.vti.ufinity.teaching.management.exception.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Getter
@Setter
@SuperBuilder
public class ApiExceptionResponse extends BaseErrorResponse{

	private String message;

}
