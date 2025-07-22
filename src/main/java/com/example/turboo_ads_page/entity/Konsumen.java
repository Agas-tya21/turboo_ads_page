package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Konsumen {
    @Id
    private String nik;
    private String namalengkap;
    private String notelpon;
    private String fotoktp;
    private String fotokk;
    private String idjaminan;
    private String idstatus;
}