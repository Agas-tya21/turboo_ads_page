package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Konsumen;
import com.example.turboo_ads_page.repository.KonsumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID; // -> Import UUID

@Service
public class KonsumenService {

    @Autowired
    private KonsumenRepository konsumenRepository;

    public List<Konsumen> getAllKonsumen() {
        return konsumenRepository.findAll();
    }

    public Optional<Konsumen> getKonsumenById(String id) {
        return konsumenRepository.findById(id);
    }

    public Konsumen createKonsumen(Konsumen konsumen) {
        // --- PERUBAHAN DI SINI: ID Konsumen digenerate otomatis ---
        // Format: KNSMN- followed by 8 random alphanumeric characters
        String id = "KNSMN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        konsumen.setIdkonsumen(id);
        
        return konsumenRepository.save(konsumen);
    }

    public Konsumen updateKonsumen(String id, Konsumen konsumenDetails) {
        Konsumen konsumen = konsumenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Konsumen not found"));
        
        konsumen.setNamalengkap(konsumenDetails.getNamalengkap());
        konsumen.setNik(konsumenDetails.getNik());
        konsumen.setNotelpon(konsumenDetails.getNotelpon());
        // Lanjutkan untuk semua field...

        return konsumenRepository.save(konsumen);
    }

    public void deleteKonsumen(String id) {
        konsumenRepository.deleteById(id);
    }
}