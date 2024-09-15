package com.exercise.lexis.demo.dto;

import com.exercise.lexis.demo.domain.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record OfficerItemDto(
        String name,
        @JsonProperty("officer_role")
        String officerRole,
        @JsonProperty("appointed_on")
        LocalDate appointedOn,
        @JsonProperty("resigned_on")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate resignedOn,
        Address address
        ) {
}
