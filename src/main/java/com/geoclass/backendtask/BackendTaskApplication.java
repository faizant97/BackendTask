package com.geoclass.backendtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackendTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTaskApplication.class, args);
	}

}
