package com.exercise.lexis.demo.client;

import com.exercise.lexis.demo.dto.CompanySearchDto;
import com.exercise.lexis.demo.dto.OfficerSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RiskClient {
    private static final String companyQuery = "/Search?Query={query}";
    private static final String officerQuery = "/Officers?CompanyNumber={companyNumber}";
    private final WebClient webClient;

    @Cacheable(cacheNames = "companies")
    public Mono<CompanySearchDto> findCompanies(String query) {
        return defaultRequest(CompanySearchDto.class, companyQuery, query);
    }

    @Cacheable(cacheNames = "officers")
    public Mono<OfficerSearchDto> findOfficers(String companyNumber) {
        return defaultRequest(OfficerSearchDto.class, officerQuery, companyNumber);
    }

    private <T> Mono<T> defaultRequest(Class<T> clazz, String query, Object...queryParams) {
        return webClient.get()
                .uri(query, queryParams)
                .retrieve()
                .bodyToMono(clazz)
                .cache();
    }
}
