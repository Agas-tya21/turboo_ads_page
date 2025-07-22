package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Cabangperusahaan;
import com.example.turboo_ads_page.repository.CabangperusahaanRepository;

@Service
public class CabangperusahaanService {
    @Autowired
    private CabangperusahaanRepository cabangperusahaanRepository;

    public List<Cabangperusahaan> getAllCabangperusahaan() {
        return cabangperusahaanRepository.findAll();
    }

    public Cabangperusahaan getCabangperusahaanById(String id) {
        return cabangperusahaanRepository.findById(id).orElse(null);
    }

    public Cabangperusahaan createCabangperusahaan(Cabangperusahaan cabangperusahaan) {
        return cabangperusahaanRepository.save(cabangperusahaan);
    }

    public Cabangperusahaan updateCabangperusahaan(String id, Cabangperusahaan cabangperusahaanDetails) {
        Cabangperusahaan cabangperusahaan = cabangperusahaanRepository.findById(id).orElse(null);
        if (cabangperusahaan != null) {
            cabangperusahaan.setNamacabang(cabangperusahaanDetails.getNamacabang());
            cabangperusahaan.setKoordinatcabang(cabangperusahaanDetails.getKoordinatcabang());
            return cabangperusahaanRepository.save(cabangperusahaan);
        }
        return null;
    }

    public void deleteCabangperusahaan(String id) {
        cabangperusahaanRepository.deleteById(id);
    }
}