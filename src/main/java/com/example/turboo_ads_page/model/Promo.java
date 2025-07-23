package com.example.turboo_ads_page.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "promo")
public class Promo {

    @Id
    @Column(name = "idpromo")
    private String idpromo;

    // Relasi ke tabel Produk
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idproduk", referencedColumnName = "idproduk")
    private Produk produk;

    @Column(name = "namapromo")
    private String namapromo;

    @Column(name = "kelaspromo")
    private String kelaspromo;

    @Column(name = "tglmulai")
    private LocalDate tglmulai;

    @Column(name = "tglakhir")
    private LocalDate tglakhir;

    @Column(name = "taglinepromo")
    private String taglinepromo;

    @Column(name = "keteranganpromo", columnDefinition = "TEXT")
    private String keteranganpromo;

    @Column(name = "gambarpromo")
    private String gambarpromo;
}