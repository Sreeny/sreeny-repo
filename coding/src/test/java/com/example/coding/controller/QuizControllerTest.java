package com.example.coding.controller;

import com.example.coding.dto.QuizResponseDto;
import com.example.coding.model.Quiz;
import com.example.coding.model.Result;
import com.example.coding.service.impl.QuizAPIDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class QuizControllerTest {

    @Mock
    private QuizAPIDataService quizAPIDataService;

    @Mock
    private QuizController quizController;

    @Mock
    private CompletableFuture<Quiz> mockQuiz;

    @WithMockUser
    @Test
    public void testGetQuizList() throws Exception{
            //setup Data
          

            //Mock data


            // Expected Data



        }


}

