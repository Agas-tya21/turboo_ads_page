package com.example.turboo_ads_page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbooAdsPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurbooAdsPageApplication.class, args);
	}

	/**
	 * Bean ini akan dijalankan sekali saat aplikasi startup.
	 * Gunakan ini untuk membuat hash BCrypt dari password Anda.
	 * Setelah Anda mendapatkan hash-nya, hapus atau komentari bean ini.
	 */
	// @Bean
	// public CommandLineRunner passwordEncoderRunner() {
	// 	return args -> {
	// 		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	// 		// Ganti "password_anda" dengan password admin yang ingin Anda gunakan
	// 		String plainPassword = "turb04dmin"; 
	// 		String encodedPassword = encoder.encode(plainPassword);
			
	// 		System.out.println("====================================================================");
	// 		System.out.println("Plain Password: " + plainPassword);
	// 		System.out.println("BCrypt Encoded Password: " + encodedPassword);
	// 		System.out.println("====================================================================");
	// 	};
	// }
}