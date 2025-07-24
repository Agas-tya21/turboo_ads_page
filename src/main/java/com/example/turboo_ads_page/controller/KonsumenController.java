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

    // --- PERUBAHAN UTAMA DI SINI ---
    // Menerima setiap file sebagai @RequestPart yang terpisah, bukan sebagai Map
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Konsumen> createKonsumen(
            @RequestPart("konsumen") String konsumenJson,
            @RequestPart(value = "fotoktp", required = false) MultipartFile fotoktp,
            @RequestPart(value = "fotokk", required = false) MultipartFile fotokk,
            @RequestPart(value = "fotojaminan", required = false) MultipartFile fotojaminan,
            @RequestPart(value = "fotorekeninglistrik", required = false) MultipartFile fotorekeninglistrik,
            @RequestPart(value = "fotoslipgaji", required = false) MultipartFile fotoslipgaji,
            @RequestPart(value = "fotopelepasanaset", required = false) MultipartFile fotopelepasanaset) {
        try {
            Konsumen konsumen = objectMapper.readValue(konsumenJson, Konsumen.class);

            // Simpan setiap file jika ada
            updateFile(konsumen::setFotoktp, fotoktp);
            updateFile(konsumen::setFotokk, fotokk);
            updateFile(konsumen::setFotojaminan, fotojaminan);
            updateFile(konsumen::setFotorekeninglistrik, fotorekeninglistrik);
            updateFile(konsumen::setFotoslipgaji, fotoslipgaji);
            updateFile(konsumen::setFotopelepasanaset, fotopelepasanaset);

            Konsumen createdKonsumen = konsumenService.createKonsumen(konsumen);
            buildFileUrls(createdKonsumen);
            return new ResponseEntity<>(createdKonsumen, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log error untuk debugging
            e.printStackTrace();
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