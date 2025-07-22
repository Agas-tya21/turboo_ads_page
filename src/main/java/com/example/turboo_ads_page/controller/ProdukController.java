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

import com.example.turboo_ads_page.entity.Produk;
import com.example.turboo_ads_page.service.ProdukService;

@RestController
@RequestMapping("/api/produk")
public class ProdukController {
    @Autowired
    private ProdukService produkService;

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
    public Produk createProduk(@RequestBody Produk produk) {
        return produkService.createProduk(produk);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produk> updateProduk(@PathVariable String id, @RequestBody Produk produkDetails) {
        Produk updatedProduk = produkService.updateProduk(id, produkDetails);
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