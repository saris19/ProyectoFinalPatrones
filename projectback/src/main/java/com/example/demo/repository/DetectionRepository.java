package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DetectionEntity;

public interface DetectionRepository extends JpaRepository<DetectionEntity, Long> {
    
}
