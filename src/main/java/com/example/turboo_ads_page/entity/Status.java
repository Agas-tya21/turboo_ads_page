package com.example.turboo_ads_page.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Status {
    @Id
    private String idstatus;
    private String namastatus;
}