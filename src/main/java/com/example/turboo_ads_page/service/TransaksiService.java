package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Transaksi;
import com.example.turboo_ads_page.repository.TransaksiRepository;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    public List<Transaksi> getAllTransaksi() {
        return transaksiRepository.findAll();
    }

    public Transaksi getTransaksiById(Long id) {
        return transaksiRepository.findById(id).orElse(null);
    }

    public Transaksi createTransaksi(Transaksi transaksi) {
        return transaksiRepository.save(transaksi);
    }

    public Transaksi updateTransaksi(Long id, Transaksi transaksiDetails) {
        Transaksi transaksi = transaksiRepository.findById(id).orElse(null);
        if (transaksi != null) {
            transaksi.setNik(transaksiDetails.getNik());
            transaksi.setIdjaminan(transaksiDetails.getIdjaminan());
            transaksi.setIdpromo(transaksiDetails.getIdpromo());
            transaksi.setIdproduk(transaksiDetails.getIdproduk());
            transaksi.setIdstatus(transaksiDetails.getIdstatus());
            return transaksiRepository.save(transaksi);
        }
        return null;
    }

    public void deleteTransaksi(Long id) {
        transaksiRepository.deleteById(id);
    }
}