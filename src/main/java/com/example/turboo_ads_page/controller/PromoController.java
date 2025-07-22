package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.entity.Promo;
import com.example.turboo_ads_page.service.PromoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/promos")
public class PromoController {

    @Autowired
    private PromoService promoService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Promo> getAllPromos() {
        return promoService.getAllPromos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promo> getPromoById(@PathVariable String id) {
        Promo promo = promoService.getPromoById(id);
        if (promo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(promo);
    }

    @PostMapping
    public Promo createPromo(@RequestParam("promo") String promoJson, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Promo promo = objectMapper.readValue(promoJson, Promo.class);
        return promoService.createPromo(promo, file);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promo> updatePromo(@PathVariable String id, @RequestParam("promo") String promoJson, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Promo promoDetails = objectMapper.readValue(promoJson, Promo.class);
        Promo updatedPromo = promoService.updatePromo(id, promoDetails, file);
        if (updatedPromo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPromo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromo(@PathVariable String id) {
        promoService.deletePromo(id);
        return ResponseEntity.noContent().build();
    }
}