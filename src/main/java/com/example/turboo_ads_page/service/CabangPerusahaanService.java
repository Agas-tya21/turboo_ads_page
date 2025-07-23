package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.CabangPerusahaan;
import com.example.turboo_ads_page.repository.CabangPerusahaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabangPerusahaanService {

    @Autowired
    private CabangPerusahaanRepository cabangRepository;

    // Create
    public CabangPerusahaan createCabang(CabangPerusahaan cabang) {
        return cabangRepository.save(cabang);
    }

    // Read All
    public List<CabangPerusahaan> getAllCabang() {
        return cabangRepository.findAll();
    }

    // Read by ID
    public Optional<CabangPerusahaan> getCabangById(String id) {
        return cabangRepository.findById(id);
    }

    // Update
    public CabangPerusahaan updateCabang(String id, CabangPerusahaan cabangDetails) {
        CabangPerusahaan cabang = cabangRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cabang not found with id: " + id));
        
        cabang.setNamacabang(cabangDetails.getNamacabang());
        cabang.setKoordinatcabang(cabangDetails.getKoordinatcabang());
        
        return cabangRepository.save(cabang);
    }

    // Delete
    public void deleteCabang(String id) {
        if (!cabangRepository.existsById(id)) {
            throw new RuntimeException("Cabang not found with id: " + id);
        }
        cabangRepository.deleteById(id);
    }

    // Count (sudah ada sebelumnya)
    public long countCabang() {
        return cabangRepository.count();
    }
}
