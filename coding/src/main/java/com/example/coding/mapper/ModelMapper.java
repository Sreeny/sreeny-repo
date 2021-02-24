package com.example.coding.mapper;

import com.example.coding.dto.QuizDto;
import com.example.coding.dto.ResultDto;
import com.example.coding.model.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapper {

    public static List<QuizDto> convertQuizEntityListToQuizDtoList(List<Result> resultList) {

        if (resultList == null) {
            return null;
        }
        List<QuizDto> quizDtoList = null;
        quizDtoList = resultList.stream().collect(Collectors.groupingBy(q -> q.category)).entrySet().stream().map(entry -> {
            QuizDto quizDto = new QuizDto();
            quizDto.setCategory(entry.getKey());
            quizDto.setResults(entry.getValue().stream().map(result -> {
                ResultDto resultDto = new ResultDto();
                resultDto.setType(result.getType());
                resultDto.setCorrectAnswer(result.getCorrectAnswer());
                resultDto.setQuestion(result.getQuestion());
                resultDto.setDifficulty(result.getDifficulty());
                List<String> allAnswers = new ArrayList<String>();
                if (result.getIncorrectAnswers() != null) {
                    allAnswers.addAll(result.getIncorrectAnswers());
                }
                allAnswers.add(result.getCorrectAnswer());
                resultDto.setAllAnswers(allAnswers);
                return resultDto;
            }).collect(Collectors.toList()));
            return quizDto;
        }).collect(Collectors.toList());

        return quizDtoList;
    }


}
