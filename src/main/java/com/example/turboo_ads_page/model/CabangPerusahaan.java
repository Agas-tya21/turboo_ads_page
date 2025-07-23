package com.example.turboo_ads_page.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cabangperusahaan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabangPerusahaan {

    @Id
    @Column(name = "idcabang")
    private String idcabang;

    @Column(name = "namacabang")
    private String namacabang;

    @Column(name = "koordinatcabang")
    private String koordinatcabang;
}