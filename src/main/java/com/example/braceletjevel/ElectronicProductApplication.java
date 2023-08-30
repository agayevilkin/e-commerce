package com.example.braceletjevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ElectronicProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicProductApplication.class, args);
    }
}
