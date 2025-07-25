package com.example.turboo_ads_page.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://192.168.18.43:3000", "http://192.168.215.192:3000", "http://localhost:3000") 
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }

    /**
     * Konfigurasi ini memberitahu Spring Boot untuk menyajikan file dari direktori lokal.
     * Ketika ada permintaan ke URL yang diawali dengan "/uploads/**",
     * Spring akan mencarikan file tersebut di dalam folder yang ditentukan oleh 'uploadDir'.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mengubah path relatif (cth: './uploads') menjadi path absolut di sistem file
        String resolvedPath = Paths.get(uploadDir).toAbsolutePath().toString();
        
        // Mendaftarkan resource handler
        // URL Path: /uploads/**
        // Lokasi File Fisik: file:/path/absolut/ke/folder/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/" + resolvedPath + "/");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Turboo Ads Page API")
                        .version("1.0")
                        .description("API Documentation for Comprofile CRUD"));
    }
}