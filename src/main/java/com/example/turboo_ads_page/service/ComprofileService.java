package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Comprofile;
import com.example.turboo_ads_page.repository.ComprofileRepository;

@Service
public class ComprofileService {
    @Autowired
    private ComprofileRepository comprofileRepository;

    public List<Comprofile> getAllComprofiles() {
        return comprofileRepository.findAll();
    }

    public Comprofile getComprofileById(String id) {
        return comprofileRepository.findById(id).orElse(null);
    }

    public Comprofile createComprofile(Comprofile comprofile) {
        return comprofileRepository.save(comprofile);
    }

    public Comprofile updateComprofile(String id, Comprofile comprofileDetails) {
        Comprofile comprofile = comprofileRepository.findById(id).orElse(null);
        if (comprofile != null) {
            comprofile.setTahun(comprofileDetails.getTahun());
            comprofile.setKonsumen(comprofileDetails.getKonsumen());
            comprofile.setIdcabang(comprofileDetails.getIdcabang());
            comprofile.setNamagedung(comprofileDetails.getNamagedung());
            comprofile.setAlamatkantorutama(comprofileDetails.getAlamatkantorutama());
            comprofile.setNomorperusahaan(comprofileDetails.getNomorperusahaan());
            comprofile.setNocustomerservice(comprofileDetails.getNocustomerservice());
            comprofile.setUsnfb(comprofileDetails.getUsnfb());
            comprofile.setUsntiktok(comprofileDetails.getUsntiktok());
            comprofile.setUsnig(comprofileDetails.getUsnig());
            comprofile.setEmail(comprofileDetails.getEmail());
            comprofile.setUsnyt(comprofileDetails.getUsnyt());
            comprofile.setUsntwt(comprofileDetails.getUsntwt());
            return comprofileRepository.save(comprofile);
        }
        return null;
    }

    public void deleteComprofile(String id) {
        comprofileRepository.deleteById(id);
    }
}