package com.example.turboo_ads_page.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "konsumen")
public class Konsumen {

    @Id
    @Column(name = "idkonsumen")
    private String idkonsumen;

    @Column(nullable = false, unique = true)
    private String nik;

    @Column(name = "namalengkap")
    private String namalengkap;

    @Column(name = "notelpon")
    private String notelpon;

    // Kolom untuk menyimpan nama file gambar
    private String fotoktp;
    private String fotokk;
    private String fotojaminan;
    private String fotorekeninglistrik;
    private String fotoslipgaji;
    private String fotopelepasanaset;

    // Relasi (diasumsikan sudah ada modelnya)
    @ManyToOne
    @JoinColumn(name = "idproduk")
    private Produk produk;

    @ManyToOne
    @JoinColumn(name = "idpromo")
    private Promo promo;

    // Diasumsikan ada model Status
    // @ManyToOne
    // @JoinColumn(name = "idstatus")
    // private Status status;
}