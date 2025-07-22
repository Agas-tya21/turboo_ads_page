package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comprofile {
    @Id
    private String idcompro;
    private String tahun;
    private String konsumen;
    private String idcabang;
    private String namagedung;
    private String alamatkantorutama;
    private String nomorperusahaan;
    private String nocustomerservice;
    private String usnfb;
    private String usntiktok;
    private String usnig;
    private String email;
    private String usnyt;
    private String usntwt;
}