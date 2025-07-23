package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Promo;
import com.example.turboo_ads_page.service.PromoService;
import com.example.turboo_ads_page.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/promo")
@Tag(name = "Promo", description = "API for Promo CRUD")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private StorageService storageService;
    
    private final ObjectMapper objectMapper;

    public PromoController() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Promo> createPromo(
            @RequestPart("promo") String promoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Promo promo = objectMapper.readValue(promoJson, Promo.class);
            if (file != null && !file.isEmpty()) {
                String fileName = storageService.storeFile(file);
                promo.setGambarpromo(fileName);
            }
            Promo createdPromo = promoService.createPromo(promo);
            buildImageUrl(createdPromo);
            return new ResponseEntity<>(createdPromo, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Promo>> getAllPromo() {
        List<Promo> promos = promoService.getAllPromo();
        promos.forEach(this::buildImageUrl);
        return ResponseEntity.ok(promos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promo> getPromoById(@PathVariable String id) {
        return promoService.getPromoById(id)
                .map(promo -> {
                    buildImageUrl(promo);
                    return ResponseEntity.ok(promo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Promo> updatePromo(
            @PathVariable String id,
            @RequestPart("promo") String promoDetailsJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            if (!promoService.getPromoById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            Promo promoDetails = objectMapper.readValue(promoDetailsJson, Promo.class);
            if (file != null && !file.isEmpty()) {
                String fileName = storageService.storeFile(file);
                promoDetails.setGambarpromo(fileName);
            }
            Promo updatedPromo = promoService.updatePromo(id, promoDetails);
            buildImageUrl(updatedPromo);
            return ResponseEntity.ok(updatedPromo);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromo(@PathVariable String id) {
        try {
            promoService.deletePromo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void buildImageUrl(Promo promo) {
        String fileName = promo.getGambarpromo();
        if (fileName != null && !fileName.isEmpty() && !fileName.startsWith("http")) {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
            promo.setGambarpromo(fileDownloadUri);
        }
    }
}