package com.example.coding.service;

import com.example.coding.model.Quiz;
import com.example.coding.model.Result;
import com.example.coding.service.impl.QuizAPIDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class QuizApiDataServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    QuizAPIDataService quizAPIDataService;

    @Test
    public void testFindQuizByAmountAndCategory() throws Exception {

        //Setup Data
        String apiUrl = "https://opentdb.com/api.php?amount=5&category=11";
        Quiz quiz = new Quiz();
        Result result = new Result();
        result.setCategory("Entertainment");
        result.setType("multiple");
        result.setDifficulty("hard");
        result.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
        result.setIncorrectAnswers(Arrays.asList("Psycho", "Vertigo", "Rebecca"));
        result.setCorrectAnswer("Rope");
        List<Result> resultList = new ArrayList<>();
        resultList.add(result);
        quiz.setResults(resultList);
        quiz.setResponseCode(0);

        // Mock
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), ArgumentMatchers.any(Class.class))).thenReturn(quiz);

        CompletableFuture<Quiz> completableFuture = quizAPIDataService.findQuizByAmountAndCategory(new Integer(1), new Integer(15));
        assertThat(completableFuture.get().responseCode).isEqualTo(quiz.responseCode);

    }
}
