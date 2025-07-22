package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Comprofile;
import com.example.turboo_ads_page.repository.ComprofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprofileService {

    @Autowired
    private ComprofileRepository comprofileRepository;

    // Create
    public Comprofile createComprofile(Comprofile comprofile) {
        return comprofileRepository.save(comprofile);
    }

    // Read All
    public List<Comprofile> getAllComprofiles() {
        return comprofileRepository.findAll();
    }

    // Read by ID
    public Optional<Comprofile> getComprofileById(String id) {
        return comprofileRepository.findById(id);
    }

    // Update
    public Comprofile updateComprofile(String id, Comprofile comprofileDetails) {
        Comprofile comprofile = comprofileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comprofile not found with id: " + id));

        comprofile.setTahun(comprofileDetails.getTahun());
        comprofile.setKonsumen(comprofileDetails.getKonsumen());
        // idcabang DIHAPUS
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

    // Delete
    public void deleteComprofile(String id) {
        if (!comprofileRepository.existsById(id)) {
            throw new RuntimeException("Comprofile not found with id: " + id);
        }
        comprofileRepository.deleteById(id);
    }
}