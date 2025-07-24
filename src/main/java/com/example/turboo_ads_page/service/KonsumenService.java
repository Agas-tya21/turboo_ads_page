package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Konsumen;
import com.example.turboo_ads_page.model.Status;
import com.example.turboo_ads_page.repository.KonsumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KonsumenService {

    @Autowired
    private KonsumenRepository konsumenRepository;

    public List<Konsumen> getAllKonsumen() {
        return konsumenRepository.findAll();
    }
    
    // --- METHOD BARU DITAMBAHKAN DI SINI ---
    public List<Konsumen> getKonsumenByStatus(String idstatus) {
        return konsumenRepository.findByStatusIdstatus(idstatus);
    }

    public Optional<Konsumen> getKonsumenById(String id) {
        return konsumenRepository.findById(id);
    }

    public Konsumen createKonsumen(Konsumen konsumen) {
        String id = "KNSMN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        konsumen.setIdkonsumen(id);

        if (konsumen.getStatus() == null) {
            Status defaultStatus = new Status();
            defaultStatus.setIdstatus("BARU");
            konsumen.setStatus(defaultStatus);
        }
        
        return konsumenRepository.save(konsumen);
    }

    public Konsumen updateKonsumen(String id, Konsumen konsumenDetails) {
        Konsumen konsumen = konsumenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Konsumen not found with id: " + id));
        
        konsumen.setNamalengkap(konsumenDetails.getNamalengkap());
        konsumen.setNik(konsumenDetails.getNik());
        konsumen.setNotelpon(konsumenDetails.getNotelpon());
        konsumen.setProduk(konsumenDetails.getProduk());
        konsumen.setJenisJaminan(konsumenDetails.getJenisJaminan());
        konsumen.setPromo(konsumenDetails.getPromo());
        konsumen.setStatus(konsumenDetails.getStatus());

        return konsumenRepository.save(konsumen);
    }

    public void deleteKonsumen(String id) {
        konsumenRepository.deleteById(id);
    }
}