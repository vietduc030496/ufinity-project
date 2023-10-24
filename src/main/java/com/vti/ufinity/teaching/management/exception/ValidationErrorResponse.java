package com.vti.ufinity.teaching.management.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorResponse {

	private HttpStatus status;

	private LocalDateTime time;

	private List<String> message;

}
