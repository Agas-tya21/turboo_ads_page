package com.example.turboo_ads_page.repository;

import com.example.turboo_ads_page.model.CabangPerusahaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabangPerusahaanRepository extends JpaRepository<CabangPerusahaan, String> {
}