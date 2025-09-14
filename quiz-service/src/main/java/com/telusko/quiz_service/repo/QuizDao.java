package com.telusko.quiz_service.repo;

import com.telusko.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
