package com.exercise.lexis.demo.domain;

import com.exercise.lexis.demo.dto.OfficerItemDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Officer(
        String name,
        @JsonProperty("officer_role")
        String officerRole,
        @JsonProperty("appointed_on")
        LocalDate appointedOn,
        Address address
        ) {

        public static Officer fromDto(OfficerItemDto officerItemDto) {
                return new Officer(
                        officerItemDto.name(),
                        officerItemDto.officerRole(),
                        officerItemDto.appointedOn(),
                        officerItemDto.address()
                );
        }
}
