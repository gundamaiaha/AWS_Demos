package com.example.quizmasterservice.repository;

import com.example.quizmasterservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
