package com.example.turboo_ads_page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.turboo_ads_page.service.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class TurbooAdsPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbooAdsPageApplication.class, args);
	}

}
