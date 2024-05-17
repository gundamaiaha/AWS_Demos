package com.example.quizmasterservice.controller;

import com.example.quizmasterservice.dto.UserQuizDataDTO;
import com.example.quizmasterservice.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {


    private final QuizService quizService;

    @GetMapping("/user-quiz-data-today")
    public List<UserQuizDataDTO> getUserQuizDataForToday() {
        return quizService.getUserQuizDataForToday();
    }
}
