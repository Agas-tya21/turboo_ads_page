package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Kelaspromo;
import com.example.turboo_ads_page.repository.KelaspromoRepository;

@Service
public class KelaspromoService {
    @Autowired
    private KelaspromoRepository kelaspromoRepository;

    public List<Kelaspromo> getAllKelaspromo() {
        return kelaspromoRepository.findAll();
    }

    public Kelaspromo getKelaspromoById(String id) {
        return kelaspromoRepository.findById(id).orElse(null);
    }

    public Kelaspromo createKelaspromo(Kelaspromo kelaspromo) {
        return kelaspromoRepository.save(kelaspromo);
    }

    public Kelaspromo updateKelaspromo(String id, Kelaspromo kelaspromoDetails) {
        Kelaspromo kelaspromo = kelaspromoRepository.findById(id).orElse(null);
        if (kelaspromo != null) {
            kelaspromo.setNamakelaspromo(kelaspromoDetails.getNamakelaspromo());
            return kelaspromoRepository.save(kelaspromo);
        }
        return null;
    }

    public void deleteKelaspromo(String id) {
        kelaspromoRepository.deleteById(id);
    }
}