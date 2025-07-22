package com.example.turboo_ads_page.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("file")
public class StorageProperties {

    private String uploadDir = "uploads";

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}