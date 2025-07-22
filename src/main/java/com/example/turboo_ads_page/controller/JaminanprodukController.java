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

import com.example.turboo_ads_page.entity.Jaminanproduk;
import com.example.turboo_ads_page.service.JaminanprodukService;

@RestController
@RequestMapping("/api/jaminan")
public class JaminanprodukController {
    @Autowired
    private JaminanprodukService jaminanprodukService;

    @GetMapping
    public List<Jaminanproduk> getAllJaminanproduk() {
        return jaminanprodukService.getAllJaminanproduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jaminanproduk> getJaminanprodukById(@PathVariable String id) {
        Jaminanproduk jaminanproduk = jaminanprodukService.getJaminanprodukById(id);
        if (jaminanproduk == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jaminanproduk);
    }

    @PostMapping
    public Jaminanproduk createJaminanproduk(@RequestBody Jaminanproduk jaminanproduk) {
        return jaminanprodukService.createJaminanproduk(jaminanproduk);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jaminanproduk> updateJaminanproduk(@PathVariable String id, @RequestBody Jaminanproduk jaminanprodukDetails) {
        Jaminanproduk updatedJaminan = jaminanprodukService.updateJaminanproduk(id, jaminanprodukDetails);
        if (updatedJaminan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedJaminan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJaminanproduk(@PathVariable String id) {
        jaminanprodukService.deleteJaminanproduk(id);
        return ResponseEntity.noContent().build();
    }
}