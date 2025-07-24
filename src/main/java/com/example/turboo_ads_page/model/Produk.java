package com.example.turboo_ads_page.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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

    @Column(name = "keteranganproduk", columnDefinition = "TEXT")
    private String keteranganproduk;

    @Column(name = "gambarproduk")
    private String gambarproduk;

    // --- BIDANG BARU DITAMBAHKAN DI SINI ---
    @OneToMany(mappedBy = "produk", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<JenisJaminan> jenisJaminans;
}