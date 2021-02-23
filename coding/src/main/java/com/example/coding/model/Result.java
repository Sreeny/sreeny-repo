package com.example.coding.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result  {
    public String category;
    public String type;
    public String difficulty;
    public String question;
    @JsonProperty("correct_answer")
    public String correctAnswer;
    @JsonProperty("incorrect_answers")
    public List<String> incorrectAnswers = new ArrayList<String>();
}
