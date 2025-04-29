package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // <-- Agregas esto
import org.springframework.web.servlet.config.annotation.CorsRegistry; // <-- Y esto
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // <-- Y esto

@SpringBootApplication
public class ProjectbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectbackApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
}
