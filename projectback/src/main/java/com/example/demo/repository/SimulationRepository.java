package com.example.demo.repository;

import com.example.demo.entity.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    // Métodos personalizados si son necesarios
}