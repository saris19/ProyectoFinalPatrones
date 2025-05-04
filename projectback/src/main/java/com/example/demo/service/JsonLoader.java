package com.example.demo.service;

import com.example.demo.entity.Detection;
import com.example.demo.service.DTO.DetectionsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JsonLoader {

    private final DetectionService detectionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loadJsonAndSaveToDb(String filePath) throws Exception {
        DetectionsWrapper wrapper = objectMapper.readValue(new File(filePath), DetectionsWrapper.class);

        List<Detection> detections = wrapper.getDetections().stream()
            .map(d -> Detection.builder()
                .timestampMs(d.getTimestamp_ms())
                .carCount(d.getObjects().getOrDefault("car", 0))
                .busCount(d.getObjects().getOrDefault("bus", 0))
                .truckCount(d.getObjects().getOrDefault("truck", 0))
                .build()
            ).collect(Collectors.toList());

        detectionService.saveDetections(detections);
    }
}

