package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.turboo_ads_page.entity.Produk;
import com.example.turboo_ads_page.repository.ProdukRepository;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private FileSystemStorageService storageService;

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Produk getProdukById(String id) {
        return produkRepository.findById(id).orElse(null);
    }

    public Produk createProduk(Produk produk, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String filename = storageService.store(file);
            produk.setGambarproduk(filename);
        }
        return produkRepository.save(produk);
    }

    public Produk updateProduk(String id, Produk produkDetails, MultipartFile file) {
        Produk produk = produkRepository.findById(id).orElse(null);
        if (produk != null) {
            produk.setNamaproduk(produkDetails.getNamaproduk());
            produk.setKeteranganproduk(produkDetails.getKeteranganproduk());
            if (file != null && !file.isEmpty()) {
                String filename = storageService.store(file);
                produk.setGambarproduk(filename);
            }
            return produkRepository.save(produk);
        }
        return null;
    }

    public void deleteProduk(String id) {
        produkRepository.deleteById(id);
    }
}