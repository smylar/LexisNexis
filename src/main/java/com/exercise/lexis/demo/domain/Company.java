package com.exercise.lexis.demo.domain;

import com.exercise.lexis.demo.dto.CompanyItemDto;
import com.exercise.lexis.demo.dto.OfficerItemDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record Company(
        @JsonProperty("company_number")
        String companyNumber,
        @JsonProperty("company_type")
        String companyType,
        String title,
        @JsonProperty("company_status")
        String companyStatus, //would likely end up as an enum
        @JsonProperty("date_of_creation")
        LocalDate dateOfCreation,
        Address address,
        List<Officer> officers
) {

    public static Company fromDto(CompanyItemDto companyItemDto, List<OfficerItemDto> officerDtoList) {
        return new Company(
                companyItemDto.companyNumber(),
                companyItemDto.companyType(),
                companyItemDto.title(),
                companyItemDto.companyStatus(),
                companyItemDto.dateOfCreation(),
                companyItemDto.address(),
                officerDtoList.stream().map(Officer::fromDto).toList()
        );
    }

}
