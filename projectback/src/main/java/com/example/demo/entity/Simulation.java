package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "simulation_name", nullable = false)
    private String simulationName;
    
    @Column(name = "start_time")
    private LocalDateTime startTime;
    
    @Column(name = "end_time")
    private LocalDateTime endTime;
    
    @Column(name = "duration_seconds")
    private Integer durationSeconds;
    
    @Column(name = "scenario_type")
    private String scenarioType;
    
    @Column(name = "weather_conditions")
    private String weatherConditions;
    
    @Column(name = "traffic_conditions")
    private String trafficConditions;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "parameters", columnDefinition = "TEXT")
    private String parameters;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}