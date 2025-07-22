package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Produk;
import com.example.turboo_ads_page.repository.ProdukRepository;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Produk getProdukById(String id) {
        return produkRepository.findById(id).orElse(null);
    }

    public Produk createProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    public Produk updateProduk(String id, Produk produkDetails) {
        Produk produk = produkRepository.findById(id).orElse(null);
        if (produk != null) {
            produk.setNamaproduk(produkDetails.getNamaproduk());
            produk.setKeteranganproduk(produkDetails.getKeteranganproduk());
            produk.setGambarproduk(produkDetails.getGambarproduk());
            return produkRepository.save(produk);
        }
        return null;
    }

    public void deleteProduk(String id) {
        produkRepository.deleteById(id);
    }
}