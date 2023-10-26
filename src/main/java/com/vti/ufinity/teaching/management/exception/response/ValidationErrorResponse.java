package com.vti.ufinity.teaching.management.exception.response;

import java.util.List;

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
public class ValidationErrorResponse extends BaseErrorResponse {

	private List<String> messages;

}
