package com.example.coding.service;

import com.example.coding.model.Quiz;

import java.util.concurrent.CompletableFuture;

public interface QuizDataService {
    public CompletableFuture<Quiz> findQuizByAmountAndCategory(Integer amount, Integer category) throws Exception;

}
