package com.example.turboo_ads_page.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.model.Produk;
import com.example.turboo_ads_page.repository.ProdukRepository;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    public Produk createProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Optional<Produk> getProdukById(String id) {
        return produkRepository.findById(id);
    }

    public Produk updateProduk(String id, Produk produkDetails) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk not found with id: " + id));
        
        produk.setNamaproduk(produkDetails.getNamaproduk());
        produk.setKeteranganproduk(produkDetails.getKeteranganproduk());
        
        // Hanya update gambar jika ada nama file baru yang diberikan
        if (produkDetails.getGambarproduk() != null && !produkDetails.getGambarproduk().isEmpty()) {
            produk.setGambarproduk(produkDetails.getGambarproduk());
        }
        
        return produkRepository.save(produk);
    }

    public void deleteProduk(String id) {
        if (!produkRepository.existsById(id)) {
            throw new RuntimeException("Produk not found with id: " + id);
        }
        produkRepository.deleteById(id);
    }
}