package com.example.turboo_ads_page.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.turboo_ads_page.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByUsnAndPassword(String usn, String password);
    Optional<Admin> findByUsn(String usn);
}