package com.exercise.lexis.demo.service;

import com.exercise.lexis.demo.client.RiskClient;
import com.exercise.lexis.demo.domain.Company;
import com.exercise.lexis.demo.dto.CompanyItemDto;
import com.exercise.lexis.demo.dto.CompanySearchDto;
import com.exercise.lexis.demo.dto.OfficerItemDto;
import com.exercise.lexis.demo.dto.OfficerSearchDto;
import com.exercise.lexis.demo.response.SearchResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class RiskService {

    private final RiskClient riskClient;

    public Mono<SearchResponse> findCompanies(@NonNull String criteria, boolean includeInactive) {
        Objects.requireNonNull(criteria);
        AtomicLong total = new AtomicLong(0);
        return riskClient.findCompanies(criteria)
                .doOnNext(dto -> total.set(dto.totalResults())) //shortcoming, as count would include inactives
                .flatMapIterable(CompanySearchDto::items)
                .filter(c -> includeInactive || c.isActive())
                .map(this::getCompanyWithOfficers)
                .flatMap(Mono::from)
                .collectList()
                .map(list -> new SearchResponse(total.get(), list));
    }

    private Mono<Company> getCompanyWithOfficers(CompanyItemDto companyItem) {
        return riskClient.findOfficers(companyItem.companyNumber())
                .subscribeOn(Schedulers.parallel()) //hope multiple request are in parallel, haven't had the time to check
                .flatMapIterable(OfficerSearchDto::items)
                .filter(this::hasNotResigned)
                .collectList()
                .map(o -> Company.fromDto(companyItem, o));
    }

    private boolean hasNotResigned(OfficerItemDto officer) {
        return officer.resignedOn() == null || officer.resignedOn().isAfter(LocalDate.now());
    }
}
