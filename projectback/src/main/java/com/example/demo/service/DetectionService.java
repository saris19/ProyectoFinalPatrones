package com.example.demo.service;

import com.example.demo.entity.DetectionEntity;
import com.example.demo.repository.DetectionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetectionService {

    @Autowired
    private DetectionRepository detectionRepository;

    /**
     * Lee el archivo JSON y lo convierte en una lista de entidades DetectionEntity.
     */
    public List<DetectionEntity> cargarDeteccionesDesdeJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("detections/detections_precision.json");

        JsonNode root = objectMapper.readTree(file);
        JsonNode detectionsArray = root.get("detections");

        List<DetectionEntity> detecciones = new ArrayList<>();

        for (JsonNode node : detectionsArray) {
            DetectionEntity detection = new DetectionEntity();
            detection.setTimestampMs(node.get("timestamp_ms").asLong());
            detection.setCar(node.get("objects").get("car").asInt());
            detection.setBus(node.get("objects").get("bus").asInt());
            detection.setTruck(node.get("objects").get("truck").asInt());
            detecciones.add(detection);
        }

        return detecciones;
    }

    /**
     * Guarda en la base de datos las detecciones leídas del archivo JSON.
     */
    public void guardarDeteccionesDesdeJson() throws IOException {
        List<DetectionEntity> detecciones = cargarDeteccionesDesdeJson();
        detectionRepository.deleteAll(); // Limpia la tabla antes de guardar (puedes quitar esto si no lo quieres)
        detectionRepository.saveAll(detecciones);
    }
    public List<DetectionEntity> getAllDetectionsFromDb() {
        return detectionRepository.findAll();
    }
    
}
