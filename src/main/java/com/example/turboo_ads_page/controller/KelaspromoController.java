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

import com.example.turboo_ads_page.entity.Kelaspromo;
import com.example.turboo_ads_page.service.KelaspromoService;

@RestController
@RequestMapping("/api/kelaspromo")
public class KelaspromoController {
    @Autowired
    private KelaspromoService kelaspromoService;

    @GetMapping
    public List<Kelaspromo> getAllKelaspromo() {
        return kelaspromoService.getAllKelaspromo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kelaspromo> getKelaspromoById(@PathVariable String id) {
        Kelaspromo kelaspromo = kelaspromoService.getKelaspromoById(id);
        if (kelaspromo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kelaspromo);
    }

    @PostMapping
    public Kelaspromo createKelaspromo(@RequestBody Kelaspromo kelaspromo) {
        return kelaspromoService.createKelaspromo(kelaspromo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kelaspromo> updateKelaspromo(@PathVariable String id, @RequestBody Kelaspromo kelaspromoDetails) {
        Kelaspromo updatedKelaspromo = kelaspromoService.updateKelaspromo(id, kelaspromoDetails);
        if (updatedKelaspromo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedKelaspromo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKelaspromo(@PathVariable String id) {
        kelaspromoService.deleteKelaspromo(id);
        return ResponseEntity.noContent().build();
    }
}