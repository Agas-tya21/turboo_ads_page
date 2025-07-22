package com.example.turboo_ads_page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turboo_ads_page.entity.Promo;
import com.example.turboo_ads_page.service.PromoService;

@RestController
@RequestMapping("/api/promos")
public class PromoController {
    @Autowired
    private PromoService promoService;

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
    public Promo createPromo(@RequestBody Promo promo) {
        return promoService.createPromo(promo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promo> updatePromo(@PathVariable String id, @RequestBody Promo promoDetails) {
        Promo updatedPromo = promoService.updatePromo(id, promoDetails);
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