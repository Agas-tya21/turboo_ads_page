package com.example.turboo_ads_page.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.model.Promo;
import com.example.turboo_ads_page.repository.PromoRepository;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    public List<Promo> getAllPromo() {
        return promoRepository.findAll();
    }

    public Optional<Promo> getPromoById(String id) {
        return promoRepository.findById(id);
    }

    public Promo createPromo(Promo promo) {
        return promoRepository.save(promo);
    }

    public Promo updatePromo(String id, Promo promoDetails) {
        Promo promo = promoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promo not found with id: " + id));

        promo.setProduk(promoDetails.getProduk());
        promo.setNamapromo(promoDetails.getNamapromo());
        promo.setKelaspromo(promoDetails.getKelaspromo());
        promo.setTglmulai(promoDetails.getTglmulai());
        promo.setTglakhir(promoDetails.getTglakhir());
        promo.setTaglinepromo(promoDetails.getTaglinepromo());
        promo.setKeteranganpromo(promoDetails.getKeteranganpromo());
        
        // Hanya update gambar jika ada nama file baru
        if (promoDetails.getGambarpromo() != null && !promoDetails.getGambarpromo().isEmpty()) {
            promo.setGambarpromo(promoDetails.getGambarpromo());
        }

        return promoRepository.save(promo);
    }

    public void deletePromo(String id) {
        if (!promoRepository.existsById(id)) {
            throw new RuntimeException("Promo not found with id: " + id);
        }
        promoRepository.deleteById(id);
    }
}