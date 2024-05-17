package com.example.quizmasterservice.controller;

import com.example.quizmasterservice.entity.Question;
import com.example.quizmasterservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/question")
public class QuestionController {


    private final QuestionService questionService;

    @GetMapping
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }


}
