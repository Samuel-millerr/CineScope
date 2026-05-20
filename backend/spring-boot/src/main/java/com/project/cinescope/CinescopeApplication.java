package com.project.cinescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CinescopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinescopeApplication.class, args);
	}


	@GetMapping("/api")
	public ResponseEntity<String> health() {
		return ResponseEntity.ok().body("OK!");
	}
}

