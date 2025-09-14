package com.telusko.quiz_service.controller;


import com.telusko.quiz_service.dto.QuizDto;
import com.telusko.quiz_service.model.QuestionWrapper;
import com.telusko.quiz_service.model.Response;
import com.telusko.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller for quiz
@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        // Call service for each category or handle multiple categories within service
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id){
        return quizService.getQuizQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
