package com.example.App_SpringBoot_One;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.App_SpringBoot_One.repo")
@EntityScan("com.example.App_SpringBoot_One.model")
@SpringBootApplication
public class AppSpringBootOneApplication {
	private static final String API_ROOT = "http://localhost:8081/api/books";

	public static void main(String[] args) {
		SpringApplication.run(AppSpringBootOneApplication.class, args);
	}

}
