package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private FileSystemStorageService storageService;

    @PostMapping("/image")
    public ResponseEntity<?> handleImageUpload(@RequestParam("file") MultipartFile file) {
        String filename = storageService.store(file);
        // Mengembalikan nama file yang disimpan untuk digunakan di client
        return ResponseEntity.ok(Map.of("filename", filename));
    }
}