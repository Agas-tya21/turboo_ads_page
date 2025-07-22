package com.example.turboo_ads_page.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.turboo_ads_page.entity.Status;
import com.example.turboo_ads_page.repository.StatusRepository;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(String id) {
        return statusRepository.findById(id).orElse(null);
    }

    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    public Status updateStatus(String id, Status statusDetails) {
        Status status = statusRepository.findById(id).orElse(null);
        if (status != null) {
            status.setNamastatus(statusDetails.getNamastatus());
            return statusRepository.save(status);
        }
        return null;
    }

    public void deleteStatus(String id) {
        statusRepository.deleteById(id);
    }
}