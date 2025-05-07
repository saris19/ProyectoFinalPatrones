package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detections")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "timestamp_ms", nullable = false)
    private Long timestampMs;
    
    @Column(name = "car_count")
    private Integer carCount;
    
    @Column(name = "bus_count")
    private Integer busCount;
    
    @Column(name = "truck_count")
    private Integer truckCount;
    
    @Column(name = "confidence", precision = 10, scale = 2)
    private Double confidence;
    
    @Column(name = "simulation_id")
    private String simulationId;
    
    @Column(name = "weather_condition")
    private String weatherCondition;
    
    @Column(name = "time_of_day")
    private String timeOfDay;
    
    @Column(name = "traffic_density")
    private String trafficDensity;
    
    @Column(name = "is_generated", columnDefinition = "boolean default true")
    private Boolean isGenerated;
    
    @Column(name = "generation_parameters", columnDefinition = "TEXT")
    private String generationParameters;
    
    @Column(name = "frame_number")
    private Integer frameNumber;
    
    @Column(name = "video_name", length = 255)
    private String videoName;
    
    @Column(name = "processed", columnDefinition = "boolean default false")
    private Boolean processed;
}


