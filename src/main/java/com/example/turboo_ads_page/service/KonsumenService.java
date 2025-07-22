package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Konsumen;
import com.example.turboo_ads_page.repository.KonsumenRepository;

@Service
public class KonsumenService {
    @Autowired
    private KonsumenRepository konsumenRepository;

    public List<Konsumen> getAllKonsumen() {
        return konsumenRepository.findAll();
    }

    public Konsumen getKonsumenByNik(String nik) {
        return konsumenRepository.findById(nik).orElse(null);
    }

    public Konsumen createKonsumen(Konsumen konsumen) {
        return konsumenRepository.save(konsumen);
    }

    public Konsumen updateKonsumen(String nik, Konsumen konsumenDetails) {
        Konsumen konsumen = konsumenRepository.findById(nik).orElse(null);
        if (konsumen != null) {
            konsumen.setNamalengkap(konsumenDetails.getNamalengkap());
            konsumen.setNotelpon(konsumenDetails.getNotelpon());
            konsumen.setFotoktp(konsumenDetails.getFotoktp());
            konsumen.setFotokk(konsumenDetails.getFotokk());
            return konsumenRepository.save(konsumen);
        }
        return null;
    }

    public void deleteKonsumen(String nik) {
        konsumenRepository.deleteById(nik);
    }
}