package com.example.turboo_ads_page.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produk")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produk {

    @Id
    @Column(name = "idproduk")
    private String idproduk;

    @Column(name = "namaproduk")
    private String namaproduk;

    // kelasproduk DIHAPUS

    @Column(name = "keteranganproduk", columnDefinition = "TEXT")
    private String keteranganproduk;

    @Column(name = "gambarproduk")
    private String gambarproduk;
}