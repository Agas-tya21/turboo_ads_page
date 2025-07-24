package com.example.turboo_ads_page.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "jenisjaminan")
public class JenisJaminan {

    @Id
    @Column(name = "idjaminan")
    private String idjaminan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idproduk", referencedColumnName = "idproduk")
    // --- ANOTASI BARU DITAMBAHKAN DI SINI ---
    @JsonBackReference
    private Produk produk;

    @Column(name = "namajaminan")
    private String namajaminan;
}