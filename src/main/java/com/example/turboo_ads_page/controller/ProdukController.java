package com.example.turboo_ads_page.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.turboo_ads_page.model.Produk;
import com.example.turboo_ads_page.service.ProdukService;
import com.example.turboo_ads_page.service.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/produk")
@Tag(name = "Produk", description = "API for Produk CRUD")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @Autowired
    private StorageService storageService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Produk> createProduk(
            @RequestPart("produk") String produkJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Produk produk = new ObjectMapper().readValue(produkJson, Produk.class);

            if (file != null && !file.isEmpty()) {
                // 1. Simpan nama file unik ke database
                String fileName = storageService.storeFile(file);
                produk.setGambarproduk(fileName);
            }
            Produk createdProduk = produkService.createProduk(produk);
            // 2. Buat URL lengkap saat mengirim respons
            buildImageUrl(createdProduk);
            return new ResponseEntity<>(createdProduk, HttpStatus.CREATED);
        } catch (IOException e) { 
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Produk>> getAllProduk() {
        List<Produk> produks = produkService.getAllProduk();
        produks.forEach(this::buildImageUrl);
        return ResponseEntity.ok(produks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        return produkService.getProdukById(id)
                .map(produk -> {
                    buildImageUrl(produk);
                    return ResponseEntity.ok(produk);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Produk> updateProduk(
            @PathVariable String id,
            @RequestPart("produk") String produkDetailsJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            Produk produkDetails = new ObjectMapper().readValue(produkDetailsJson, Produk.class);
            
            if (!produkService.getProdukById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            if (file != null && !file.isEmpty()) {
                String fileName = storageService.storeFile(file);
                produkDetails.setGambarproduk(fileName);
            }

            Produk updatedProduk = produkService.updateProduk(id, produkDetails);
            buildImageUrl(updatedProduk);
            return ResponseEntity.ok(updatedProduk);

        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    /**
     * Metode helper untuk membangun URL gambar yang lengkap.
     * @param produk Objek Produk yang akan dimodifikasi.
     */
    private void buildImageUrl(Produk produk) {
        String fileName = produk.getGambarproduk();
        if (fileName != null && !fileName.isEmpty() && !fileName.startsWith("http")) {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
            produk.setGambarproduk(fileDownloadUri);
        }
    }
}