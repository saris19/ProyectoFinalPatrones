package com.example.demo.service;

import com.example.demo.entity.Detection;
import com.example.demo.repository.DetectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetectionService {

    private final DetectionRepository repository;

    public void saveDetections(List<Detection> detections) {
        repository.saveAll(detections);
    }

    public List<Detection> getAllDetections() {
        return repository.findAll();
    }
}
