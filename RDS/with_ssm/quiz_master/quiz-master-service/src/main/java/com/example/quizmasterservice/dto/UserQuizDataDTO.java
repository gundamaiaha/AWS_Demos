package com.example.quizmasterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserQuizDataDTO {
    private Long userId;
    private String username;
    private Long quizId;
    private String quizTitle;
    private String quizDescription;
    private Long questionId;
    private String questionText;
    private Long choiceId;
    private String choiceText;
    private Boolean isCorrect;
    private Long userAnswerId;
    private LocalDateTime answeredAt;
    private Integer score;
    private LocalDateTime submittedAt;

}
