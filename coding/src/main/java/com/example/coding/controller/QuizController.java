package com.example.coding.controller;

import com.example.coding.dto.QuizDto;
import com.example.coding.dto.QuizResponseDto;
import com.example.coding.mapper.ModelMapper;
import com.example.coding.model.Quiz;
import com.example.coding.model.Result;
import com.example.coding.service.QuizDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/coding/exercise/quiz")
public class QuizController {

    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
    @Autowired
    private QuizDataService quizDataService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<QuizResponseDto> getQuizList() throws Exception {
        logger.info("Entered getQuizList of QuizController and getting quiz list");
        CompletableFuture<Quiz> quiz1 = quizDataService.findQuizByAmountAndCategory(5, 11);
        CompletableFuture<Quiz> quiz2 = quizDataService.findQuizByAmountAndCategory(5, 12);
        CompletableFuture.allOf(quiz1, quiz2).join();
        List<Result> resultList = new ArrayList<Result>();

        if (quiz1 == null || quiz1.get() == null || quiz1.get().getResponseCode() != 0) {
           logger.error("Can not get quiz for amount = 5 and category 11");
           throw  new Exception("Can not get quiz for amount =5 and category 11");
        }

        if (quiz2 == null || quiz2.get() == null || quiz2.get().getResponseCode() != 0) {
            logger.error("Can not get quiz for amount = 5 and category 12");
            throw  new Exception("Can not get quiz for amount =5 and category 12");
        }

        resultList.addAll(quiz1.get().results);
        resultList.addAll(quiz2.get().results);

        if (resultList.size() == 0) {
            logger.info("No Quiz items found!!");
            logger.info("Exit getQuizList of QuizController and getting quiz list");
            return ResponseEntity.notFound().build();
        }

        logger.info("Exit getQuizList of QuizController and getting quiz list");
        return ResponseEntity.ok().body(new QuizResponseDto(ModelMapper.convertQuizEntityListToQuizDtoList(resultList)));

    }
}
