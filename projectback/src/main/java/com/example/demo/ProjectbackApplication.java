package com.example.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.service.JsonLoader;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjectbackApplication {

    private final JsonLoader jsonLoader;

    public static void main(String[] args) {
        SpringApplication.run(ProjectbackApplication.class, args);
    }

    @PostConstruct
    public void runOnStartup() throws Exception {
        String filePath = "../detections/detections_precision.json";
        jsonLoader.loadJsonAndSaveToDb(filePath);
        System.out.println("✅ Detections imported at startup!");
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
