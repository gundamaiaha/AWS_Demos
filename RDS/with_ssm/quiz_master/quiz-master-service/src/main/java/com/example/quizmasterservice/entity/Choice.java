package com.example.quizmasterservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private String choiceText;
    private Boolean isCorrect;
    // Getters and Setters
}
