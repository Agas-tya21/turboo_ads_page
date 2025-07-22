package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.turboo_ads_page.entity.Jaminanproduk;
import com.example.turboo_ads_page.repository.JaminanprodukRepository;

@Service
public class JaminanprodukService {

    @Autowired
    private JaminanprodukRepository jaminanprodukRepository;

    @Autowired
    private FileSystemStorageService storageService;

    public List<Jaminanproduk> getAllJaminanproduk() {
        return jaminanprodukRepository.findAll();
    }

    public Jaminanproduk getJaminanprodukById(String id) {
        return jaminanprodukRepository.findById(id).orElse(null);
    }

    public Jaminanproduk createJaminanproduk(Jaminanproduk jaminanproduk, MultipartFile fotojaminan, MultipartFile fotorekeninglistrik, MultipartFile fotoslipgaji, MultipartFile fotopelepasanaset) {
        if (fotojaminan != null && !fotojaminan.isEmpty()) {
            jaminanproduk.setFotojaminan(storageService.store(fotojaminan));
        }
        if (fotorekeninglistrik != null && !fotorekeninglistrik.isEmpty()) {
            jaminanproduk.setFotorekeninglistrik(storageService.store(fotorekeninglistrik));
        }
        if (fotoslipgaji != null && !fotoslipgaji.isEmpty()) {
            jaminanproduk.setFotoslipgaji(storageService.store(fotoslipgaji));
        }
        if (fotopelepasanaset != null && !fotopelepasanaset.isEmpty()) {
            jaminanproduk.setFotopelepasanaset(storageService.store(fotopelepasanaset));
        }
        return jaminanprodukRepository.save(jaminanproduk);
    }

    public Jaminanproduk updateJaminanproduk(String id, Jaminanproduk jaminanprodukDetails, MultipartFile fotojaminan, MultipartFile fotorekeninglistrik, MultipartFile fotoslipgaji, MultipartFile fotopelepasanaset) {
        Jaminanproduk jaminanproduk = jaminanprodukRepository.findById(id).orElse(null);
        if (jaminanproduk != null) {
            if (fotojaminan != null && !fotojaminan.isEmpty()) {
                jaminanproduk.setFotojaminan(storageService.store(fotojaminan));
            }
            if (fotorekeninglistrik != null && !fotorekeninglistrik.isEmpty()) {
                jaminanproduk.setFotorekeninglistrik(storageService.store(fotorekeninglistrik));
            }
            if (fotoslipgaji != null && !fotoslipgaji.isEmpty()) {
                jaminanproduk.setFotoslipgaji(storageService.store(fotoslipgaji));
            }
            if (fotopelepasanaset != null && !fotopelepasanaset.isEmpty()) {
                jaminanproduk.setFotopelepasanaset(storageService.store(fotopelepasanaset));
            }
            return jaminanprodukRepository.save(jaminanproduk);
        }
        return null;
    }

    public void deleteJaminanproduk(String id) {
        jaminanprodukRepository.deleteById(id);
    }
}