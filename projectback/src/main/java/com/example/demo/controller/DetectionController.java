package com.example.demo.controller;

import com.example.demo.service.DetectionService;
import com.example.demo.service.JsonLoader;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detections")
@RequiredArgsConstructor
public class DetectionController {

    private final JsonLoader jsonLoader;
    private final DetectionService detectionService;

    @PostMapping("/import") //manual
    public String importDetections() throws Exception {
        String filePath = "../detections/detections_precision.json";
        jsonLoader.loadJsonAndSaveToDb(filePath);
        return "Detections imported!";
    }

    @GetMapping("/analysis/summary")
    public Map<String, Object> getAnalysisSummary() {
        return detectionService.calculateAnalysis();
    }

}
