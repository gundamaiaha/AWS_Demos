package com.example.quizmasterservice.service;

import com.example.quizmasterservice.entity.Question;
import com.example.quizmasterservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;


    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }




}
