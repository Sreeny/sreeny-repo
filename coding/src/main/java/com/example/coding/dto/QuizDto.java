package com.example.coding.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuizDto {
    private String category;
    List<ResultDto> results;
}
