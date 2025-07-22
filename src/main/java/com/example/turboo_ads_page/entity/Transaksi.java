package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtransaksi; // Sebelumnya 'id', sekarang menjadi 'idtransaksi'
    private String nik;
    private String idjaminan;
    private String idpromo;
    private String idproduk;
    private String idstatus;
}