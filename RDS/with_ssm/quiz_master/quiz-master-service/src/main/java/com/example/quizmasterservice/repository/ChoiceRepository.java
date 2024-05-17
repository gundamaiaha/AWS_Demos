package com.example.quizmasterservice.repository;

import com.example.quizmasterservice.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
