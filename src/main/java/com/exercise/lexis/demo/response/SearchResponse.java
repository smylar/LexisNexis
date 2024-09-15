package com.exercise.lexis.demo.response;

import com.exercise.lexis.demo.domain.Company;

import java.util.List;

public record SearchResponse(
    long totalResults,
    List<Company> items) {
}
