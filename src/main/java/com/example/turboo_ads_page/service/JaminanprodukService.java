package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Jaminanproduk;
import com.example.turboo_ads_page.repository.JaminanprodukRepository;

@Service
public class JaminanprodukService {
    @Autowired
    private JaminanprodukRepository jaminanprodukRepository;

    public List<Jaminanproduk> getAllJaminanproduk() {
        return jaminanprodukRepository.findAll();
    }

    public Jaminanproduk getJaminanprodukById(String id) {
        return jaminanprodukRepository.findById(id).orElse(null);
    }

    public Jaminanproduk createJaminanproduk(Jaminanproduk jaminanproduk) {
        return jaminanprodukRepository.save(jaminanproduk);
    }

    public Jaminanproduk updateJaminanproduk(String id, Jaminanproduk jaminanprodukDetails) {
        Jaminanproduk jaminanproduk = jaminanprodukRepository.findById(id).orElse(null);
        if (jaminanproduk != null) {
            jaminanproduk.setFotojaminan(jaminanprodukDetails.getFotojaminan());
            jaminanproduk.setFotorekeninglistrik(jaminanprodukDetails.getFotorekeninglistrik());
            jaminanproduk.setFotoslipgaji(jaminanprodukDetails.getFotoslipgaji());
            jaminanproduk.setFotopelepasanaset(jaminanprodukDetails.getFotopelepasanaset());
            return jaminanprodukRepository.save(jaminanproduk);
        }
        return null;
    }

    public void deleteJaminanproduk(String id) {
        jaminanprodukRepository.deleteById(id);
    }
}