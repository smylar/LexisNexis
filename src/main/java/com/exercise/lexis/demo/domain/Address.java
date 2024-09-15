package com.exercise.lexis.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(
        String premises,
        String locality,
        @JsonProperty("address_line_1")
        String addressLine,
        String country,
        @JsonProperty("postal_code")
        String postalCode
) {
}
