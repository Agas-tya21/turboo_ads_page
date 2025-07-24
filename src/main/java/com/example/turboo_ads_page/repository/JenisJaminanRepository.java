package com.example.turboo_ads_page.repository;

import com.example.turboo_ads_page.model.JenisJaminan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List

@Repository
public interface JenisJaminanRepository extends JpaRepository<JenisJaminan, String> {
    // --- METHOD BARU DITAMBAHKAN DI SINI ---
    List<JenisJaminan> findByProdukIdproduk(String idProduk);
}