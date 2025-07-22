package com.example.turboo_ads_page.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getUploadDir());
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    public String store(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = "";
        int i = originalFilename.lastIndexOf('.');
        if (i > 0) {
            extension = originalFilename.substring(i);
        }
        String newFilename = UUID.randomUUID().toString() + extension;

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + newFilename);
            }
            if (newFilename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + newFilename);
            }
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(newFilename),
                        StandardCopyOption.REPLACE_EXISTING);
                return newFilename;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + newFilename, e);
        }
    }
}