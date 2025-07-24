package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Admin;
import com.example.turboo_ads_page.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> login(String usn, String password) {
        return adminRepository.findByUsnAndPassword(usn, password);
    }
}