package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Kategoripromo;
import com.example.turboo_ads_page.repository.KategoripromoRepository;

@Service
public class KategoripromoService {
    @Autowired
    private KategoripromoRepository kategoripromoRepository;

    public List<Kategoripromo> getAllKategoripromo() {
        return kategoripromoRepository.findAll();
    }

    public Kategoripromo getKategoripromoById(String id) {
        return kategoripromoRepository.findById(id).orElse(null);
    }

    public Kategoripromo createKategoripromo(Kategoripromo kategoripromo) {
        return kategoripromoRepository.save(kategoripromo);
    }

    public Kategoripromo updateKategoripromo(String id, Kategoripromo kategoripromoDetails) {
        Kategoripromo kategoripromo = kategoripromoRepository.findById(id).orElse(null);
        if (kategoripromo != null) {
            kategoripromo.setNamakategoripromo(kategoripromoDetails.getNamakategoripromo());
            return kategoripromoRepository.save(kategoripromo);
        }
        return null;
    }

    public void deleteKategoripromo(String id) {
        kategoripromoRepository.deleteById(id);
    }
}