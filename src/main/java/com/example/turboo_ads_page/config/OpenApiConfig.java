package com.example.turboo_ads_page.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}