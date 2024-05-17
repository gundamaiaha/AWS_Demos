package com.example.quizmasterservice.service;

import com.example.quizmasterservice.dto.UserQuizDataDTO;
import com.example.quizmasterservice.repository.UserAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {


    private final UserAnswerRepository userAnswerRepository;

    public List<UserQuizDataDTO> getUserQuizDataForToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        return userAnswerRepository.findUserQuizDataForToday(startOfDay, endOfDay);
    }
}
