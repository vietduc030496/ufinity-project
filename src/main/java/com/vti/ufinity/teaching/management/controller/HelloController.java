package com.vti.ufinity.teaching.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@RestController
public class HelloController {

	@GetMapping({"/", "/hello"})
	public ResponseEntity<String> sayHello() {

		return ResponseEntity.ok("Hello Spring Boot");
	}

}
