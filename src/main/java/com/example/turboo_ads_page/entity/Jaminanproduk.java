package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Jaminanproduk {
    @Id
    private String idjaminan;
    private String fotojaminan;
    private String fotorekeninglistrik;
    private String fotoslipgaji;
    private String fotopelepasanaset;
}