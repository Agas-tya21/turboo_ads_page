package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.turboo_ads_page.entity.Promo;
import com.example.turboo_ads_page.repository.PromoRepository;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private FileSystemStorageService storageService;

    public List<Promo> getAllPromos() {
        return promoRepository.findAll();
    }

    public Promo getPromoById(String id) {
        return promoRepository.findById(id).orElse(null);
    }

    public Promo createPromo(Promo promo, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String filename = storageService.store(file);
            promo.setGambarpromo(filename);
        }
        return promoRepository.save(promo);
    }

    public Promo updatePromo(String id, Promo promoDetails, MultipartFile file) {
        Promo promo = promoRepository.findById(id).orElse(null);
        if (promo != null) {
            promo.setIdkategoripromo(promoDetails.getIdkategoripromo());
            promo.setNamapromo(promoDetails.getNamapromo());
            promo.setTglmulai(promoDetails.getTglmulai());
            promo.setTglakhir(promoDetails.getTglakhir());
            promo.setIdproduk(promoDetails.getIdproduk());
            promo.setIdkelaspromo(promoDetails.getIdkelaspromo());
            promo.setTaglinepromo(promoDetails.getTaglinepromo());
            promo.setKeteranganpromo(promoDetails.getKeteranganpromo());

            if (file != null && !file.isEmpty()) {
                String filename = storageService.store(file);
                promo.setGambarpromo(filename);
            }
            return promoRepository.save(promo);
        }
        return null;
    }

    public void deletePromo(String id) {
        promoRepository.deleteById(id);
    }
}