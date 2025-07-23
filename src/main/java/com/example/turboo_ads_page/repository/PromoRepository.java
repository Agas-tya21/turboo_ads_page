package com.example.turboo_ads_page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboo_ads_page.model.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, String> {
    // JpaRepository sudah menyediakan metode CRUD dasar
}