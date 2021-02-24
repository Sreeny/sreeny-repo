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
public class Quiz {
    @JsonProperty("response_code")
    public Integer responseCode;
    public List<Result> results = new ArrayList<Result>();
}
