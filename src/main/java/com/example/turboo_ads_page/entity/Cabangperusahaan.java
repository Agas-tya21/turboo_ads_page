package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cabangperusahaan {
    @Id
    private String idcabang;
    private String namacabang;
    private String koordinatcabang;
}