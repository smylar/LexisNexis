package com.exercise.lexis.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CompanySearchDto (
        @JsonProperty("page_number")
        long pageNumber,
        String kind,
        @JsonProperty("total_results")
        long totalResults,
        List<CompanyItemDto> items
){
}
