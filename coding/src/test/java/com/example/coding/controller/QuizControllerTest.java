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
            Integer amount =  new Integer(5);
            Integer category = new Integer(11);

            //Mock data

            Quiz quiz1 = new Quiz();

            quiz1.setResponseCode(0);
            Result result =  new Result();
            result.setCategory("Entertainment");
            result.setType("multiple");
            result.setDifficulty("hard");
            result.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
            result.setIncorrectAnswers(Arrays.asList("Psycho","Vertigo","Rebecca"));
            result.setCorrectAnswer("Rope");
            CompletableFuture<Quiz> completableFuture = CompletableFuture.completedFuture(quiz1);

            Mockito.mockStatic(CompletableFuture.class);

            quiz1.setResults(Arrays.asList(result));
            Mockito.doReturn(completableFuture).when(quizAPIDataService).findQuizByAmountAndCategory(amount, category);
            Mockito.doReturn(ResponseEntity.ok().build()).when(quizController).getQuizList();

            // Expected Data
           ResponseEntity<QuizResponseDto>  responseEntity =  quizController.getQuizList();
            verify(quizAPIDataService, times(1)).findQuizByAmountAndCategory(any(Integer.class), any(Integer.class));
           assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


        }


}

