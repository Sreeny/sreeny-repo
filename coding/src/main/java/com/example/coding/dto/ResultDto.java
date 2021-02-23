package com.example.coding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private String type;
    private String difficulty;
    private String question;
    private List<String> allAnswers =  new ArrayList<String>();
    private String correctAnswer;
}
