package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Produk {
    @Id
    private String idproduk;
    private String namaproduk;
    private String keteranganproduk;
    private String gambarproduk;
}