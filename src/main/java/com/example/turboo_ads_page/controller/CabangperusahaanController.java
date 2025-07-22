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

import com.example.turboo_ads_page.entity.Cabangperusahaan;
import com.example.turboo_ads_page.service.CabangperusahaanService;

@RestController
@RequestMapping("/api/cabang")
public class CabangperusahaanController {
    @Autowired
    private CabangperusahaanService cabangperusahaanService;

    @GetMapping
    public List<Cabangperusahaan> getAllCabangperusahaan() {
        return cabangperusahaanService.getAllCabangperusahaan();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabangperusahaan> getCabangperusahaanById(@PathVariable String id) {
        Cabangperusahaan cabangperusahaan = cabangperusahaanService.getCabangperusahaanById(id);
        if (cabangperusahaan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cabangperusahaan);
    }

    @PostMapping
    public Cabangperusahaan createCabangperusahaan(@RequestBody Cabangperusahaan cabangperusahaan) {
        return cabangperusahaanService.createCabangperusahaan(cabangperusahaan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cabangperusahaan> updateCabangperusahaan(@PathVariable String id, @RequestBody Cabangperusahaan cabangperusahaanDetails) {
        Cabangperusahaan updatedCabang = cabangperusahaanService.updateCabangperusahaan(id, cabangperusahaanDetails);
        if (updatedCabang == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCabang);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabangperusahaan(@PathVariable String id) {
        cabangperusahaanService.deleteCabangperusahaan(id);
        return ResponseEntity.noContent().build();
    }
}