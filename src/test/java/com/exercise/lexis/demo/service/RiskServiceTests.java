package com.exercise.lexis.demo.service;

import com.exercise.lexis.demo.client.RiskClient;
import com.exercise.lexis.demo.domain.Company;
import com.exercise.lexis.demo.domain.Officer;
import com.exercise.lexis.demo.dto.CompanyItemDto;
import com.exercise.lexis.demo.dto.CompanySearchDto;
import com.exercise.lexis.demo.dto.OfficerItemDto;
import com.exercise.lexis.demo.dto.OfficerSearchDto;
import com.exercise.lexis.demo.response.SearchResponse;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RiskServiceTests {

    RiskClient mockClient = mock(RiskClient.class);

    RiskService riskService = new RiskService(mockClient);

    @Test
    void success_fully_generated_search_response() {
        LocalDate now = LocalDate.now();
        String criteria = "ABC";
        CompanySearchDto companyResponse = new CompanySearchDto(
                1,
                "kind",
                1,
                Collections.singletonList(
                        new CompanyItemDto("123", "desc", "type", criteria, "active", now, null)
                )
        );

        OfficerSearchDto officerResponse = new OfficerSearchDto(
                "tag",
                "kind",
                1,
                Collections.singletonList(
                        new OfficerItemDto("name", "role", LocalDate.MIN, null, null)
                )
        );

        when(mockClient.findCompanies(criteria)).thenReturn(Mono.just(companyResponse));
        when(mockClient.findOfficers(companyResponse.items().getFirst().companyNumber())).thenReturn(Mono.just(officerResponse));

        SearchResponse response = riskService.findCompanies(criteria, false).block();

        SearchResponse expectedResponse = new SearchResponse(
                1,
                Collections.singletonList(
                        new Company(
                                "123",
                                "type",
                                criteria,
                                "active",
                                now,
                                null,
                                Collections.singletonList(
                                        new Officer(
                                              "name",
                                              "role",
                                              LocalDate.MIN,
                                              null
                                        )
                                )
                        )
                )
        );

        assertThat(response, equalTo(expectedResponse));

    }
}
