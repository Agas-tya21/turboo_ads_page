package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.entity.Jaminanproduk;
import com.example.turboo_ads_page.service.JaminanprodukService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jaminan")
public class JaminanprodukController {

    @Autowired
    private JaminanprodukService jaminanprodukService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public Jaminanproduk createJaminanproduk(@RequestParam("jaminan") String jaminanJson,
                                            @RequestParam(value = "fotojaminan", required = false) MultipartFile fotojaminan,
                                            @RequestParam(value = "fotorekeninglistrik", required = false) MultipartFile fotorekeninglistrik,
                                            @RequestParam(value = "fotoslipgaji", required = false) MultipartFile fotoslipgaji,
                                            @RequestParam(value = "fotopelepasanaset", required = false) MultipartFile fotopelepasanaset) throws IOException {
        Jaminanproduk jaminanproduk = objectMapper.readValue(jaminanJson, Jaminanproduk.class);
        return jaminanprodukService.createJaminanproduk(jaminanproduk, fotojaminan, fotorekeninglistrik, fotoslipgaji, fotopelepasanaset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jaminanproduk> updateJaminanproduk(@PathVariable String id,
                                                            @RequestParam("jaminan") String jaminanJson,
                                                            @RequestParam(value = "fotojaminan", required = false) MultipartFile fotojaminan,
                                                            @RequestParam(value = "fotorekeninglistrik", required = false) MultipartFile fotorekeninglistrik,
                                                            @RequestParam(value = "fotoslipgaji", required = false) MultipartFile fotoslipgaji,
                                                            @RequestParam(value = "fotopelepasanaset", required = false) MultipartFile fotopelepasanaset) throws IOException {
        Jaminanproduk jaminanprodukDetails = objectMapper.readValue(jaminanJson, Jaminanproduk.class);
        Jaminanproduk updatedJaminan = jaminanprodukService.updateJaminanproduk(id, jaminanprodukDetails, fotojaminan, fotorekeninglistrik, fotoslipgaji, fotopelepasanaset);
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