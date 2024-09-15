package com.exercise.lexis.demo.dto;

import com.exercise.lexis.demo.domain.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CompanyItemDto(

        @JsonProperty("company_number")
        String companyNumber,
        String description,

        @JsonProperty("company_type")
        String companyType,
        String title,
        @JsonProperty("company_status")
        String companyStatus,
        @JsonProperty("date_of_creation")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dateOfCreation,
        Address address
) {

    public boolean isActive() {
        return "active".equalsIgnoreCase(companyStatus);
    }
}
