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

import com.example.turboo_ads_page.entity.Transaksi;
import com.example.turboo_ads_page.service.TransaksiService;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public List<Transaksi> getAllTransaksi() {
        return transaksiService.getAllTransaksi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getTransaksiById(@PathVariable Long id) {
        Transaksi transaksi = transaksiService.getTransaksiById(id);
        if (transaksi == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaksi);
    }

    @PostMapping
    public Transaksi createTransaksi(@RequestBody Transaksi transaksi) {
        return transaksiService.createTransaksi(transaksi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaksi> updateTransaksi(@PathVariable Long id, @RequestBody Transaksi transaksiDetails) {
        Transaksi updatedTransaksi = transaksiService.updateTransaksi(id, transaksiDetails);
        if (updatedTransaksi == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTransaksi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaksi(@PathVariable Long id) {
        transaksiService.deleteTransaksi(id);
        return ResponseEntity.noContent().build();
    }
}