package com.exercise.lexis.demo.client;

import com.exercise.lexis.demo.dto.CompanySearchDto;
import com.exercise.lexis.demo.dto.OfficerSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class RiskClient {
    private static final String companyQuery = "/Search?Query={query}";
    private static final String officerQuery = "/Officers?CompanyNumber={companyNumber}";
    private static final Duration TTL = Duration.ofMinutes(5); //config
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
                .doOnError(HttpStatusCodeException.class,
                        ex -> log.error("Query: [{}] failed. Status: [{}] Body: [{}]", query, ex.getStatusCode(), ex.getResponseBodyAsString()))
                .cache(b -> TTL, e -> Duration.ZERO, () -> Duration.ZERO);
    }
}
