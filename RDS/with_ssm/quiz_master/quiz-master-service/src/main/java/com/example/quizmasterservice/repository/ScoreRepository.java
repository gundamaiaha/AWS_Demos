package com.example.quizmasterservice.repository;

import com.example.quizmasterservice.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
