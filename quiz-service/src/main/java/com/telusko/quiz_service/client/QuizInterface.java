package com.telusko.quiz_service.client;

import com.telusko.quiz_service.model.QuestionWrapper;
import com.telusko.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam List<String> categories,
                                                            @RequestParam Integer numQ);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);


    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
