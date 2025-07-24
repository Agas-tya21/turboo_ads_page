package com.example.turboo_ads_page.controller;

import com.example.turboo_ads_page.model.Status;
import com.example.turboo_ads_page.service.StatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
@Tag(name = "Status", description = "API for Status CRUD")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        return new ResponseEntity<>(statusService.createStatus(status), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable String id) {
        try {
            statusService.deleteStatus(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}