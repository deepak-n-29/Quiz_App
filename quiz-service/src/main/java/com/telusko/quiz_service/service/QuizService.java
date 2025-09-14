package com.telusko.quiz_service.service;

import com.telusko.quiz_service.client.QuizInterface;
import com.telusko.quiz_service.model.QuestionWrapper;
import com.telusko.quiz_service.model.Quiz;
import com.telusko.quiz_service.model.Response;
import com.telusko.quiz_service.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(List<String> categories, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(categories,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(Integer id) {
        Quiz quiz = quizDao.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        List<Integer> questionIds = quiz.getQuestionIds();
        List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromId(questionIds).getBody();

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Integer score = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
