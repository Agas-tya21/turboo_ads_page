package com.example.turboo_ads_page.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private Path rootLocation;

    // Inisialisasi folder upload
    public void init() {
        try {
            rootLocation = Paths.get(uploadDir);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    // Fungsi untuk menyimpan file
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }
        
        // Buat nama file yang unik untuk menghindari duplikasi
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFilename = UUID.randomUUID().toString() + extension;

        try {
            Path destinationFile = this.rootLocation.resolve(Paths.get(newFilename))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // Security check
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            
            return newFilename; // Kembalikan nama file baru untuk disimpan di database
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}