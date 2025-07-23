package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Produk;
import com.example.turboo_ads_page.service.ProdukService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produk")
@Tag(name = "Produk", description = "API for Produk CRUD")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @PostMapping
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk) {
        return new ResponseEntity<>(produkService.createProduk(produk), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Produk>> getAllProduk() {
        return ResponseEntity.ok(produkService.getAllProduk());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        return produkService.getProdukById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable String id, @RequestBody Produk produkDetails) {
        try {
            return ResponseEntity.ok(produkService.updateProduk(id, produkDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduk(@PathVariable String id) {
        try {
            produkService.deleteProduk(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}