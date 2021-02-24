package com.example.coding.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResultDto {
    private String type;
    private String difficulty;
    private String question;
    private List<String> allAnswers =  new ArrayList<String>();
    private String correctAnswer;
}
