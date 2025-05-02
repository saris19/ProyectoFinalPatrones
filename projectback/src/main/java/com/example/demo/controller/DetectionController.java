package com.example.demo.controller;

import com.example.demo.service.JsonLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detections")
@RequiredArgsConstructor
public class DetectionController {

    private final JsonLoader jsonLoader;

    @PostMapping("/import")
    public String importDetections() throws Exception {
        String filePath = "../detections/detections_precision.json";
        jsonLoader.loadJsonAndSaveToDb(filePath);
        return "Detections imported!";
    }
}
