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

import com.example.turboo_ads_page.entity.Kategoripromo;
import com.example.turboo_ads_page.service.KategoripromoService;

@RestController
@RequestMapping("/api/kategoripromo")
public class KategoripromoController {
    @Autowired
    private KategoripromoService kategoripromoService;

    @GetMapping
    public List<Kategoripromo> getAllKategoripromo() {
        return kategoripromoService.getAllKategoripromo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategoripromo> getKategoripromoById(@PathVariable String id) {
        Kategoripromo kategoripromo = kategoripromoService.getKategoripromoById(id);
        if (kategoripromo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kategoripromo);
    }

    @PostMapping
    public Kategoripromo createKategoripromo(@RequestBody Kategoripromo kategoripromo) {
        return kategoripromoService.createKategoripromo(kategoripromo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kategoripromo> updateKategoripromo(@PathVariable String id, @RequestBody Kategoripromo kategoripromoDetails) {
        Kategoripromo updatedKategoripromo = kategoripromoService.updateKategoripromo(id, kategoripromoDetails);
        if (updatedKategoripromo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedKategoripromo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategoripromo(@PathVariable String id) {
        kategoripromoService.deleteKategoripromo(id);
        return ResponseEntity.noContent().build();
    }
}