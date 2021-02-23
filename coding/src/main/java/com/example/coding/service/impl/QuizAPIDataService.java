package com.example.coding.service.impl;

import com.example.coding.model.Quiz;
import com.example.coding.service.QuizDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class QuizAPIDataService implements QuizDataService {

    private static final Logger logger = LoggerFactory.getLogger(QuizAPIDataService.class);

    private final RestTemplate restTemplate;

    @Value("${opentdb.api.url}")
    private String apiUrl;

    public QuizAPIDataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    @Async
    public CompletableFuture<Quiz> findQuizByAmountAndCategory(Integer amount, Integer category) throws Exception {
        logger.info(String.format("Finding Quiz Items for amount %s and Category %s",amount, category));
        if(amount == null || category == null) {
            logger.error("Invalid parameters!!");
            throw new Exception("Invalid parameters");
        }
        StringBuilder queryString = new StringBuilder("?").append("amount=").append(amount).append("&").append("category=").append(category);
        String url = apiUrl + queryString;
        Quiz quiz = restTemplate.getForObject(url, Quiz.class);
        logger.debug("Found "+quiz.results.size() +"Quiz Items");
        return CompletableFuture.completedFuture(quiz);
    }
}
