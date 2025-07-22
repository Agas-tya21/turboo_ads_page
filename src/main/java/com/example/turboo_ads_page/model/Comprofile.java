package com.example.turboo_ads_page.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comprofile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comprofile {

    @Id
    @Column(name = "idcompro")
    private String idcompro;

    @Column(name = "tahun")
    private String tahun;

    @Column(name = "konsumen")
    private String konsumen;

    // idcabang DIHAPUS

    @Column(name = "namagedung")
    private String namagedung;

    @Column(name = "alamatkantorutama")
    private String alamatkantorutama;

    @Column(name = "nomorperusahaan")
    private String nomorperusahaan;

    @Column(name = "nocustomerservice")
    private String nocustomerservice;

    @Column(name = "usnfb")
    private String usnfb;

    @Column(name = "usntiktok")
    private String usntiktok;

    @Column(name = "usnig")
    private String usnig;

    @Column(name = "email")
    private String email;

    @Column(name = "usnyt")
    private String usnyt;

    @Column(name = "usntwt")
    private String usntwt;
}