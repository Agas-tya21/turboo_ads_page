package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.entity.Produk;
import com.example.turboo_ads_page.service.ProdukService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public List<Produk> getAllProduk() {
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        Produk produk = produkService.getProdukById(id);
        if (produk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produk);
    }

    @PostMapping
    public Produk createProduk(@RequestParam("produk") String produkJson, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Produk produk = objectMapper.readValue(produkJson, Produk.class);
        return produkService.createProduk(produk, file);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable String id, @RequestParam("produk") String produkJson, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Produk produkDetails = objectMapper.readValue(produkJson, Produk.class);
        Produk updatedProduk = produkService.updateProduk(id, produkDetails, file);
        if (updatedProduk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable String id) {
        produkService.deleteProduk(id);
        return ResponseEntity.noContent().build();
    }
}