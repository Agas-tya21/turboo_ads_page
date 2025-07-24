package com.example.turboo_ads_page.repository;

import com.example.turboo_ads_page.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByUsnAndPassword(String usn, String password);
}