package com.example.coding.mapper;

import com.example.coding.dto.QuizDto;
import com.example.coding.dto.ResultDto;
import com.example.coding.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ModelMapperTest {

    @Test
    public void testConvertQuizEntityListToQuizDtoListWhenOneQuizAndResponse() {

        // Setup Data
        List<Result> resultList = new ArrayList<>();
        Result result = new Result();
        result.setCategory("Entertainment");
        result.setType("multiple");
        result.setDifficulty("hard");
        result.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
        result.setIncorrectAnswers(Arrays.asList("Psycho", "Vertigo", "Rebecca"));
        result.setCorrectAnswer("Rope");
        resultList.add(result);

        // Expected
        List<QuizDto> expectedQuizDtoList = new ArrayList<>();
        QuizDto quizDto = new QuizDto();
        quizDto.setCategory("Entertainment");

        List<ResultDto> resultDtoList = new ArrayList<>();
        ResultDto resultDto = new ResultDto();
        resultDto.setType("multiple");
        resultDto.setDifficulty("hard");
        resultDto.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
        resultDto.setAllAnswers(Arrays.asList("Psycho", "Vertigo", "Rebecca", "Rope"));
        resultDto.setCorrectAnswer("Rope");
        resultDtoList.add(resultDto);
        quizDto.setResults(resultDtoList);
        expectedQuizDtoList.add(quizDto);

        List<QuizDto> quizDtoList = ModelMapper.convertQuizEntityListToQuizDtoList(resultList);
        assertThat(quizDtoList.size()).isEqualTo(1);
        assertThat(quizDtoList).isEqualTo(expectedQuizDtoList);
    }


    @Test
    public void testConvertQuizEntityListToQuizDtoListWhenMoreThanOneQuizAndResponse() {

        // Setup Data
        List<Result> resultList = new ArrayList<>();
        Result result = new Result();
        result.setCategory("Entertainment");
        result.setType("multiple");
        result.setDifficulty("hard");
        result.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
        result.setIncorrectAnswers(Arrays.asList("Psycho", "Vertigo", "Rebecca"));
        result.setCorrectAnswer("Rope");
        resultList.add(result);
        Result result2 = new Result();
        result2.setCategory("Entertainment:Film");
        result2.setType("multiple, difficulty");
        result2.setDifficulty("easy");
        result2.setQuestion("Who Plays Jack Burton in the movie Big Trouble in Little China?");
        result2.setIncorrectAnswers(Arrays.asList("Patrick Swayze", "John Cusack", "Harrison Ford"));
        result2.setCorrectAnswer("Kurt Russell");
        resultList.add(result2);

        // Expected
        List<QuizDto> expectedQuizDtoList = new ArrayList<>();
        QuizDto quizDto = new QuizDto();
        quizDto.setCategory("Entertainment");

        List<ResultDto> resultDtoList = new ArrayList<>();
        ResultDto resultDto = new ResultDto();
        resultDto.setType("multiple");
        resultDto.setDifficulty("hard");
        resultDto.setQuestion("Which was the first of Alfred Hitchcock&amp;#039;s movies to be filmed in colour?");
        resultDto.setAllAnswers(Arrays.asList("Psycho", "Vertigo", "Rebecca", "Rope"));
        resultDto.setCorrectAnswer("Rope");
        resultDtoList.add(resultDto);
        quizDto.setResults(resultDtoList);
        expectedQuizDtoList.add(quizDto);
        QuizDto quizDto2 = new QuizDto();
        quizDto2.setCategory("Entertainment:Film");

        List<ResultDto> resultDtoList2 = new ArrayList<>();
        ResultDto resultDto2 = new ResultDto();
        resultDto2.setType("multiple, difficulty");
        resultDto2.setDifficulty("easy");
        resultDto2.setQuestion("Who Plays Jack Burton in the movie Big Trouble in Little China?");
        resultDto2.setAllAnswers(Arrays.asList("Patrick Swayze", "John Cusack", "Harrison Ford", "Kurt Russell"));
        resultDto2.setCorrectAnswer("Kurt Russell");
        resultDtoList2.add(resultDto2);
        quizDto2.setResults(resultDtoList2);
        expectedQuizDtoList.add(quizDto2);

        List<QuizDto> quizDtoList = ModelMapper.convertQuizEntityListToQuizDtoList(resultList);
        assertThat(quizDtoList.size()).isEqualTo(2);
        assertThat(quizDtoList).isEqualTo(expectedQuizDtoList);
    }

    @Test
    public void testConvertQuizEntityListToQuizDtoListWhenNullListPassed() {

        // Setup Data
        List<Result> resultList = null;

        List<QuizDto> quizDtoList = ModelMapper.convertQuizEntityListToQuizDtoList(resultList);
        assertThat(quizDtoList).isNull();

    }
}
