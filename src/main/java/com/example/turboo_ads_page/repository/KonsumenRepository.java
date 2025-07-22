package com.example.turboo_ads_page.repository;

import com.example.turboo_ads_page.entity.Konsumen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonsumenRepository extends JpaRepository<Konsumen, String> {
}