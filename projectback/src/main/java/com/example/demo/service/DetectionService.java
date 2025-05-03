package com.example.demo.service;

import com.example.demo.entity.Detection;
import com.example.demo.repository.DetectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> calculateAnalysis() {
    List<Detection> detections = repository.findAll();

    int sumCars = detections.stream().mapToInt(Detection::getCarCount).sum();
    int sumBuses = detections.stream().mapToInt(Detection::getBusCount).sum();
    int sumTrucks = detections.stream().mapToInt(Detection::getTruckCount).sum();

    int frames = detections.size();  

    int maxCars = detections.stream().mapToInt(Detection::getCarCount).max().orElse(0);
    int maxBuses = detections.stream().mapToInt(Detection::getBusCount).max().orElse(0);
    int maxTrucks = detections.stream().mapToInt(Detection::getTruckCount).max().orElse(0);

    Map<String, Object> analysis = new HashMap<>();
    analysis.put("avg_cars", sumCars / frames);
    analysis.put("avg_buses", sumBuses / frames);
    analysis.put("avg_trucks", sumTrucks / frames);

    analysis.put("max_cars", maxCars);
    analysis.put("max_buses", maxBuses);
    analysis.put("max_trucks", maxTrucks);

    analysis.put("frames", frames);

    return analysis;
}

}
