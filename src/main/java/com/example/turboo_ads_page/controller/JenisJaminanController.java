package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.JenisJaminan;
import com.example.turboo_ads_page.service.JenisJaminanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jenisjaminan")
@Tag(name = "Jenis Jaminan", description = "API for Jenis Jaminan CRUD")
public class JenisJaminanController {

    @Autowired
    private JenisJaminanService jenisJaminanService;

    @GetMapping
    public ResponseEntity<List<JenisJaminan>> getAllJenisJaminan() {
        return ResponseEntity.ok(jenisJaminanService.getAllJenisJaminan());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JenisJaminan> getJenisJaminanById(@PathVariable String id) {
        return jenisJaminanService.getJenisJaminanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- ENDPOINT BARU DITAMBAHKAN DI SINI ---
    @GetMapping("/produk/{idProduk}")
    public ResponseEntity<List<JenisJaminan>> getJaminanByProdukId(@PathVariable String idProduk) {
        return ResponseEntity.ok(jenisJaminanService.getJaminanByProdukId(idProduk));
    }

    @PostMapping
    public ResponseEntity<JenisJaminan> createJenisJaminan(@RequestBody JenisJaminan jenisJaminan) {
        return new ResponseEntity<>(jenisJaminanService.createJenisJaminan(jenisJaminan), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JenisJaminan> updateJenisJaminan(@PathVariable String id, @RequestBody JenisJaminan jaminanDetails) {
        try {
            return ResponseEntity.ok(jenisJaminanService.updateJenisJaminan(id, jaminanDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJenisJaminan(@PathVariable String id) {
        try {
            jenisJaminanService.deleteJenisJaminan(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}