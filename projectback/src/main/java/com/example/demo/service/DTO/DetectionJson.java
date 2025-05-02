package com.example.demo.service.DTO;



import lombok.Data;
import java.util.Map;

@Data
public class DetectionJson {
    private Long timestamp_ms;
    private Map<String, Integer> objects;
}

