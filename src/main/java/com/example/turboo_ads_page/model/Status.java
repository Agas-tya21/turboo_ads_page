package com.example.turboo_ads_page.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "idstatus")
    private String idstatus;

    @Column(name = "namastatus")
    private String namastatus;
}