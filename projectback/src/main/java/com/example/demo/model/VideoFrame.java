package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "video_frames")
public class VideoFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "frame_number")
    private Integer frameNumber;
    
    @Column(name = "timestamp")
    private Long timestamp;
    
    @Column(name = "vehicles_detected")
    @ElementCollection
    private List<VehicleDetection> vehiclesDetected;
    
    @Column(name = "traffic_density")
    private String trafficDensity;
    
    @Column(name = "weather_condition")
    private String weatherCondition;
    
    @Column(name = "light_condition")
    private String lightCondition;
    
    @Column(name = "confidence_score")
    private Double confidenceScore;
}