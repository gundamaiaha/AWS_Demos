package com.example.quizmasterservice.repository;

import com.example.quizmasterservice.dto.UserQuizDataDTO;
import com.example.quizmasterservice.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT new com.example.quizmasterservice.dto.UserQuizDataDTO(" +
            "u.userId, u.username, qz.quizId, qz.title, qz.description, " +
            "qs.questionId, qs.questionText, c.choiceId, c.choiceText, c.isCorrect, " +
            "ua.userAnswerId, ua.answeredAt, s.score, s.submittedAt) " +
            "FROM UserAnswer ua " +
            "JOIN ua.user u " +
            "JOIN ua.question qs " +
            "JOIN ua.choice c " +
            "JOIN qs.quiz qz " +
            "JOIN Score s ON u.userId = s.user.userId AND qz.quizId = s.quiz.quizId " +
            "WHERE ua.answeredAt >= :startOfDay AND ua.answeredAt < :endOfDay")
    List<UserQuizDataDTO> findUserQuizDataForToday(@Param("startOfDay") LocalDateTime startOfDay,
                                                   @Param("endOfDay") LocalDateTime endOfDay);
}
