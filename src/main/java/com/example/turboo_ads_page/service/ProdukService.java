package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Produk;
import com.example.turboo_ads_page.repository.ProdukRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        // kelasproduk DIHAPUS
        produk.setKeteranganproduk(produkDetails.getKeteranganproduk());
        produk.setGambarproduk(produkDetails.getGambarproduk());
        
        return produkRepository.save(produk);
    }

    public void deleteProduk(String id) {
        if (!produkRepository.existsById(id)) {
            throw new RuntimeException("Produk not found with id: " + id);
        }
        produkRepository.deleteById(id);
    }
}