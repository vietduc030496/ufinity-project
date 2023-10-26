package com.vti.ufinity.teaching.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootTeachingSystemManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTeachingSystemManagementApplication.class, args);
	}

}
