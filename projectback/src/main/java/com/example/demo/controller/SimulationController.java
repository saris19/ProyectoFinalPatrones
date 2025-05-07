package com.example.demo.controller;

import com.example.demo.entity.Simulation;
import com.example.demo.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulations")
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;
    
    @PostMapping
    public ResponseEntity<Simulation> createSimulation(@RequestBody Simulation simulation) {
        return ResponseEntity.ok(simulationService.createSimulation(simulation));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Simulation> getSimulation(@PathVariable Long id) {
        return ResponseEntity.ok(simulationService.getSimulation(id));
    }
    
    // Otros endpoints necesarios
}