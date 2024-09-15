package com.exercise.lexis.demo.request;

public record SearchRequest(
        String companyNumber,
        String companyName
) {
}
