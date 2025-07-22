package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.entity.Konsumen;
import com.example.turboo_ads_page.service.KonsumenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/konsumen")
public class KonsumenController {

    @Autowired
    private KonsumenService konsumenService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public Konsumen createKonsumen(@RequestParam("konsumen") String konsumenJson, @RequestParam(value = "fotoktp", required = false) MultipartFile fotoktp, @RequestParam(value = "fotokk", required = false) MultipartFile fotokk) throws IOException {
        Konsumen konsumen = objectMapper.readValue(konsumenJson, Konsumen.class);
        return konsumenService.createKonsumen(konsumen, fotoktp, fotokk);
    }

    @PutMapping("/{nik}")
    public ResponseEntity<Konsumen> updateKonsumen(@PathVariable String nik, @RequestParam("konsumen") String konsumenJson, @RequestParam(value = "fotoktp", required = false) MultipartFile fotoktp, @RequestParam(value = "fotokk", required = false) MultipartFile fotokk) throws IOException {
        Konsumen konsumenDetails = objectMapper.readValue(konsumenJson, Konsumen.class);
        Konsumen updatedKonsumen = konsumenService.updateKonsumen(nik, konsumenDetails, fotoktp, fotokk);
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