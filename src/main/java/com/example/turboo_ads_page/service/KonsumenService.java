package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.entity.Konsumen;
import com.example.turboo_ads_page.repository.KonsumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class KonsumenService {

    @Autowired
    private KonsumenRepository konsumenRepository;

    @Autowired
    private FileSystemStorageService storageService;

    public List<Konsumen> getAllKonsumen() {
        return konsumenRepository.findAll();
    }

    public Konsumen getKonsumenByNik(String nik) {
        return konsumenRepository.findById(nik).orElse(null);
    }

    public Konsumen createKonsumen(Konsumen konsumen, MultipartFile fotoktp, MultipartFile fotokk) {
        if (fotoktp != null && !fotoktp.isEmpty()) {
            String filename = storageService.store(fotoktp);
            konsumen.setFotoktp(filename);
        }
        if (fotokk != null && !fotokk.isEmpty()) {
            String filename = storageService.store(fotokk);
            konsumen.setFotokk(filename);
        }
        return konsumenRepository.save(konsumen);
    }

    public Konsumen updateKonsumen(String nik, Konsumen konsumenDetails, MultipartFile fotoktp, MultipartFile fotokk) {
        Konsumen konsumen = konsumenRepository.findById(nik).orElse(null);
        if (konsumen != null) {
            konsumen.setNamalengkap(konsumenDetails.getNamalengkap());
            konsumen.setNotelpon(konsumenDetails.getNotelpon());
            if (fotoktp != null && !fotoktp.isEmpty()) {
                String filename = storageService.store(fotoktp);
                konsumen.setFotoktp(filename);
            }
            if (fotokk != null && !fotokk.isEmpty()) {
                String filename = storageService.store(fotokk);
                konsumen.setFotokk(filename);
            }
            return konsumenRepository.save(konsumen);
        }
        return null;
    }

    public void deleteKonsumen(String nik) {
        konsumenRepository.deleteById(nik);
    }
}