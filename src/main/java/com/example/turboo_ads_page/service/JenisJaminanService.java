package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.JenisJaminan;
import com.example.turboo_ads_page.repository.JenisJaminanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JenisJaminanService {

    @Autowired
    private JenisJaminanRepository jenisJaminanRepository;

    public List<JenisJaminan> getAllJenisJaminan() {
        return jenisJaminanRepository.findAll();
    }

    public Optional<JenisJaminan> getJenisJaminanById(String id) {
        return jenisJaminanRepository.findById(id);
    }

    public JenisJaminan createJenisJaminan(JenisJaminan jenisJaminan) {
        String id = "JMN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        jenisJaminan.setIdjaminan(id);
        return jenisJaminanRepository.save(jenisJaminan);
    }

    public JenisJaminan updateJenisJaminan(String id, JenisJaminan jaminanDetails) {
        JenisJaminan jaminan = jenisJaminanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jenis Jaminan not found with id: " + id));
        jaminan.setProduk(jaminanDetails.getProduk());
        jaminan.setNamajaminan(jaminanDetails.getNamajaminan());
        return jenisJaminanRepository.save(jaminan);
    }

    public void deleteJenisJaminan(String id) {
        if (!jenisJaminanRepository.existsById(id)) {
            throw new RuntimeException("Jenis Jaminan not found with id: " + id);
        }
        jenisJaminanRepository.deleteById(id);
    }

    // --- METHOD BARU DITAMBAHKAN DI SINI ---
    public List<JenisJaminan> getJaminanByProdukId(String idProduk) {
        return jenisJaminanRepository.findByProdukIdproduk(idProduk);
    }
}