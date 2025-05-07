package com.example.demo.service;

import com.example.demo.entity.Simulation;
import com.example.demo.repository.SimulationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimulationService {
    private final SimulationRepository simulationRepository;
    
    public Simulation createSimulation(Simulation simulation) {
        return simulationRepository.save(simulation);
    }
    
    public Simulation getSimulation(Long id) {
        return simulationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simulación no encontrada"));
    }
    
    // Otros métodos de servicio necesarios
}