package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Konsumen;
import com.example.turboo_ads_page.service.KonsumenService;
import com.example.turboo_ads_page.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/konsumen")
public class KonsumenController {

    @Autowired
    private KonsumenService konsumenService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Konsumen> createKonsumen(
            @RequestPart("konsumen") String konsumenJson,
            @RequestPart(value = "files", required = false) Map<String, MultipartFile> files) {
        try {
            Konsumen konsumen = objectMapper.readValue(konsumenJson, Konsumen.class);

            if (files != null) {
                updateFile(konsumen::setFotoktp, files.get("fotoktp"));
                updateFile(konsumen::setFotokk, files.get("fotokk"));
                updateFile(konsumen::setFotojaminan, files.get("fotojaminan"));
                updateFile(konsumen::setFotorekeninglistrik, files.get("fotorekeninglistrik"));
                updateFile(konsumen::setFotoslipgaji, files.get("fotoslipgaji"));
                updateFile(konsumen::setFotopelepasanaset, files.get("fotopelepasanaset"));
            }

            Konsumen createdKonsumen = konsumenService.createKonsumen(konsumen);
            return new ResponseEntity<>(createdKonsumen, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Konsumen>> getAllKonsumen() {
        List<Konsumen> list = konsumenService.getAllKonsumen();
        list.forEach(this::buildFileUrls);
        return ResponseEntity.ok(list);
    }
    
    // Helper methods
    private void updateFile(Consumer<String> setter, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            setter.accept(storageService.storeFile(file));
        }
    }

    private void buildFileUrls(Konsumen konsumen) {
        konsumen.setFotoktp(createFileUrl(konsumen.getFotoktp()));
        konsumen.setFotokk(createFileUrl(konsumen.getFotokk()));
        konsumen.setFotojaminan(createFileUrl(konsumen.getFotojaminan()));
        konsumen.setFotorekeninglistrik(createFileUrl(konsumen.getFotorekeninglistrik()));
        konsumen.setFotoslipgaji(createFileUrl(konsumen.getFotoslipgaji()));
        konsumen.setFotopelepasanaset(createFileUrl(konsumen.getFotopelepasanaset()));
    }

    private String createFileUrl(String fileName) {
        if (fileName == null || fileName.isEmpty() || fileName.startsWith("http")) {
            return fileName;
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();
    }
}