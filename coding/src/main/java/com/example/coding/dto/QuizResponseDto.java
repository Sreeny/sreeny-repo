package com.example.coding.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuizResponseDto {
    private List<QuizDto> quiz;
}
