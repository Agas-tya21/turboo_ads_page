package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Kategoripromo {
    @Id
    private String idkategoripromo;
    private String namakategoripromo;
}