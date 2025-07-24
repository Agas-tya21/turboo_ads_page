package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Konsumen;
import com.example.turboo_ads_page.service.KonsumenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/konsumen")
@Tag(name = "Konsumen", description = "API for Konsumen CRUD")
public class KonsumenController {

    @Autowired
    private KonsumenService konsumenService;

    @PostMapping
    public ResponseEntity<Konsumen> createKonsumen(@RequestBody Konsumen konsumen) {
        try {
            Konsumen createdKonsumen = konsumenService.createKonsumen(konsumen);
            return new ResponseEntity<>(createdKonsumen, HttpStatus.CREATED);
        } catch (Exception e) {
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

    // --- ENDPOINT BARU DITAMBAHKAN DI SINI ---
    @GetMapping("/status/{idstatus}")
    public ResponseEntity<List<Konsumen>> getKonsumenByStatus(@PathVariable String idstatus) {
        List<Konsumen> list = konsumenService.getKonsumenByStatus(idstatus);
        list.forEach(this::buildFileUrls);
        return ResponseEntity.ok(list);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Konsumen> getKonsumenById(@PathVariable String id) {
        return konsumenService.getKonsumenById(id)
                .map(konsumen -> {
                    buildFileUrls(konsumen);
                    return ResponseEntity.ok(konsumen);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Konsumen> updateKonsumen(@PathVariable String id, @RequestBody Konsumen konsumenDetails) {
        try {
            Konsumen updatedKonsumen = konsumenService.updateKonsumen(id, konsumenDetails);
            buildFileUrls(updatedKonsumen);
            return ResponseEntity.ok(updatedKonsumen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKonsumen(@PathVariable String id) {
        try {
            konsumenService.deleteKonsumen(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void buildFileUrls(Konsumen konsumen) {}

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