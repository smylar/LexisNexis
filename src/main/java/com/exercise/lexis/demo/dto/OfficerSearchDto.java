package com.exercise.lexis.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OfficerSearchDto (
        @JsonProperty("etag")
        String eTag,
        String kind,
        @JsonProperty("items_per_page")
        long itemsPerPage,
        List<OfficerItemDto> items
){
}
