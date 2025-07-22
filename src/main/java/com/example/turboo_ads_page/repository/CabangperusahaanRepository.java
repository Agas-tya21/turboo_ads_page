package com.example.turboo_ads_page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboo_ads_page.entity.Cabangperusahaan;

@Repository
public interface CabangperusahaanRepository extends JpaRepository<Cabangperusahaan, String> {
}