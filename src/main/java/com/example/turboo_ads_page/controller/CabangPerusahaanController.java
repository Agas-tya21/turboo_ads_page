package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.CabangPerusahaan;
import com.example.turboo_ads_page.service.CabangPerusahaanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cabang")
@Tag(name = "Cabang Perusahaan", description = "API for Branch CRUD")
public class CabangPerusahaanController {

    @Autowired
    private CabangPerusahaanService cabangService;

    // Create
    @PostMapping
    public ResponseEntity<CabangPerusahaan> createCabang(@RequestBody CabangPerusahaan cabang) {
        return new ResponseEntity<>(cabangService.createCabang(cabang), HttpStatus.CREATED);
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<CabangPerusahaan>> getAllCabang() {
        return ResponseEntity.ok(cabangService.getAllCabang());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<CabangPerusahaan> getCabangById(@PathVariable String id) {
        return cabangService.getCabangById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<CabangPerusahaan> updateCabang(@PathVariable String id, @RequestBody CabangPerusahaan cabangDetails) {
        try {
            return ResponseEntity.ok(cabangService.updateCabang(id, cabangDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabang(@PathVariable String id) {
        try {
            cabangService.deleteCabang(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getCabangCount() {
        long count = cabangService.countCabang();
        return ResponseEntity.ok(Map.of("total", count));
    }
}