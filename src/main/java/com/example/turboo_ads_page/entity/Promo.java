package com.example.turboo_ads_page.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Promo {
    @Id
    private String idpromo;
    private String idkategoripromo;
    private String namapromo;
    private Date tglmulai;
    private Date tglakhir;
    private String idproduk;
    private String idkelaspromo;
    private String taglinepromo;
    private String keteranganpromo;
    private String gambarpromo;
}