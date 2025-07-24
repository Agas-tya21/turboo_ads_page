package com.example.turboo_ads_page.service;

import com.example.turboo_ads_page.model.Status;
import com.example.turboo_ads_page.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(String id) {
        if (!statusRepository.existsById(id)) {
            throw new RuntimeException("Status not found with id: " + id);
        }
        statusRepository.deleteById(id);
    }
}