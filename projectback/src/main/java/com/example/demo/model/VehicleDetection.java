package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class VehicleDetection {
    private String vehicleType;
    private Double positionX;
    private Double positionY;
    private Double speed;
    private Double confidence;
}