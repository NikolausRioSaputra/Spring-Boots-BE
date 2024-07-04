package com.example.learnSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LearnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}



}
