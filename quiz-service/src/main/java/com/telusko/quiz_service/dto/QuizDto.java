package com.telusko.quiz_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizDto {
    List<String> categoryName;
    Integer numQuestions;
    String title;
}
