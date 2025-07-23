package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.service.StorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "File Upload", description = "API for uploading files")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        storageService.init(); // Pastikan direktori ada
        String fileName = storageService.store(file);

        // Buat URL yang bisa diakses untuk file yang diunggah
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(Map.of("url", fileDownloadUri));
    }
}