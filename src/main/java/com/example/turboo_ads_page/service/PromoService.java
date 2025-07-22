package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Promo;
import com.example.turboo_ads_page.repository.PromoRepository;

@Service
public class PromoService {
    @Autowired
    private PromoRepository promoRepository;

    public List<Promo> getAllPromos() {
        return promoRepository.findAll();
    }

    public Promo getPromoById(String id) {
        return promoRepository.findById(id).orElse(null);
    }

    public Promo createPromo(Promo promo) {
        return promoRepository.save(promo);
    }

    public Promo updatePromo(String id, Promo promoDetails) {
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
            promo.setGambarpromo(promoDetails.getGambarpromo());
            return promoRepository.save(promo);
        }
        return null;
    }

    public void deletePromo(String id) {
        promoRepository.deleteById(id);
    }
}