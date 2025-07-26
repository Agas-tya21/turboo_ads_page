package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Admin;
import com.example.turboo_ads_page.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> login(String usn, String password) {
        return adminRepository.findByUsnAndPassword(usn, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByUsn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}