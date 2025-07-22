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

import com.example.turboo_ads_page.entity.Konsumen;
import com.example.turboo_ads_page.service.KonsumenService;

@RestController
@RequestMapping("/api/konsumen")
public class KonsumenController {
    @Autowired
    private KonsumenService konsumenService;

    @GetMapping
    public List<Konsumen> getAllKonsumen() {
        return konsumenService.getAllKonsumen();
    }

    @GetMapping("/{nik}")
    public ResponseEntity<Konsumen> getKonsumenByNik(@PathVariable String nik) {
        Konsumen konsumen = konsumenService.getKonsumenByNik(nik);
        if (konsumen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(konsumen);
    }

    @PostMapping
    public Konsumen createKonsumen(@RequestBody Konsumen konsumen) {
        return konsumenService.createKonsumen(konsumen);
    }

    @PutMapping("/{nik}")
    public ResponseEntity<Konsumen> updateKonsumen(@PathVariable String nik, @RequestBody Konsumen konsumenDetails) {
        Konsumen updatedKonsumen = konsumenService.updateKonsumen(nik, konsumenDetails);
        if (updatedKonsumen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedKonsumen);
    }

    @DeleteMapping("/{nik}")
    public ResponseEntity<Void> deleteKonsumen(@PathVariable String nik) {
        konsumenService.deleteKonsumen(nik);
        return ResponseEntity.noContent().build();
    }
}