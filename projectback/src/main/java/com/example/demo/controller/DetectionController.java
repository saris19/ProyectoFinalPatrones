package com.example.demo.controller;

import com.example.demo.entity.DetectionEntity;
import com.example.demo.service.DetectionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/detecciones")
public class DetectionController {

    @Autowired
    private DetectionService detectionService;

    /**
     * Endpoint para leer el JSON directamente y mostrarlo (sin guardar en la base de datos).
     */
    @GetMapping
    public List<Map<String, Object>> getDetectionsFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("detections/detections_precision.json");
            Map<String, Object> jsonMap = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
            return (List<Map<String, Object>>) jsonMap.get("detections");
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Endpoint para obtener todos los registros desde la base de datos.
     */
    @GetMapping("/bd")
    public List<DetectionEntity> getAllFromDatabase() {
        return detectionService.getAllDetectionsFromDb();
    }

    /**
     * (Opcional) Cargar manualmente el JSON y guardarlo en la base de datos.
     */
    @PostMapping("/cargar-json")
    public String cargarJson() {
        try {
            detectionService.guardarDeteccionesDesdeJson();
            return "Datos cargados exitosamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al cargar los datos.";
        }
    }
}


