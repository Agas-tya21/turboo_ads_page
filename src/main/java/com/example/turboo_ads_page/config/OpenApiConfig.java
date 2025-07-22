package com.example.turboo_ads_page.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Turboo Ads Page API")
                        .version("1.0.0")
                        .description("Dokumentasi API untuk aplikasi Turboo Ads Page. API ini menangani semua operasi terkait data promo, produk, konsumen, dan lainnya."));
    }

    // Tambahkan Bean ini untuk konfigurasi CORS
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Terapkan ke semua endpoint di bawah /api
                        .allowedOrigins("http://localhost:3000", "http://192.168.215.192:3000", "http://192.168.215.192:9090") // Izinkan origin frontend Anda
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Izinkan metode HTTP yang diperlukan
                        .allowedHeaders("*") // Izinkan semua header
                        .allowCredentials(true);
            }
        };
    }
}