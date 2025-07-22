package com.example.turboo_ads_page.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.turboo_ads_page.entity.Comprofile;
import com.example.turboo_ads_page.service.ComprofileService;

@RestController
@RequestMapping("/api/comprofiles")
public class ComprofileController {
    @Autowired
    private ComprofileService comprofileService;

    @GetMapping
    public List<Comprofile> getAllComprofiles() {
        return comprofileService.getAllComprofiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprofile> getComprofileById(@PathVariable String id) {
        Comprofile comprofile = comprofileService.getComprofileById(id);
        if (comprofile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comprofile);
    }

    @PostMapping
    public Comprofile createComprofile(@RequestBody Comprofile comprofile) {
        return comprofileService.createComprofile(comprofile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comprofile> updateComprofile(@PathVariable String id, @RequestBody Comprofile comprofileDetails) {
        Comprofile updatedComprofile = comprofileService.updateComprofile(id, comprofileDetails);
        if (updatedComprofile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedComprofile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComprofile(@PathVariable String id) {
        comprofileService.deleteComprofile(id);
        return ResponseEntity.noContent().build();
    }
}