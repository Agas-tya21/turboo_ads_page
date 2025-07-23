package com.example.turboo_ads_page.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Menerapkan CORS pada semua endpoint di bawah /api/
                .allowedOrigins("http://192.168.1.14:3000", "http://192.168.215.192:3000") // Mengizinkan semua domain. Ganti dengan domain frontend Anda untuk produksi.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metode HTTP yang diizinkan
                .allowedHeaders("*") // Mengizinkan semua header
                .allowCredentials(false);
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