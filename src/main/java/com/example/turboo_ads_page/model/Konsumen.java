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

    @Column(nullable = false)
    private String nik;

    @Column(name = "namalengkap")
    private String namalengkap;

    @Column(name = "notelpon")
    private String notelpon;
    
    @ManyToOne
    @JoinColumn(name = "idproduk")
    private Produk produk;

    @ManyToOne
    @JoinColumn(name = "idpromo")
    private Promo promo;

    @ManyToOne
    @JoinColumn(name = "idjaminan")
    private JenisJaminan jenisJaminan;

    @ManyToOne
    @JoinColumn(name = "idstatus")
    private Status status;
}