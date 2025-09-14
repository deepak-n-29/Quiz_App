package com.telusko.question_service.service;


import com.telusko.question_service.model.Question;
import com.telusko.question_service.model.QuestionWrapper;
import com.telusko.question_service.model.Response;
import com.telusko.question_service.repo.QuestionDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ModelMapper modelMapper;



    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }


    public ResponseEntity<String> addMultipleQuestions(List<Question> questions) {
        try{
            questionDao.saveAll(questions);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(List<String> categories, Integer numQ) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategories(categories, numQ);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questions = questionDao.findAllById(questionIds);

        List<QuestionWrapper> questionWrappers = questions.stream()
                .map(question -> modelMapper.map(question,QuestionWrapper.class))
                .toList();

        return ResponseEntity.ok(questionWrappers);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;
        for(Response response : responses){
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
