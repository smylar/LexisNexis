package com.exercise.lexis.demo.controller;

import com.exercise.lexis.demo.request.SearchRequest;
import com.exercise.lexis.demo.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RiskController {

    private final RiskService riskService;

    @PostMapping("/search")
    public Mono<ResponseEntity<?>> search(@RequestBody SearchRequest searchRequest,
                                                      @RequestParam(name = "includeInactive", required = false) Boolean includeInactive) {

        if (searchRequest.companyName() == null && searchRequest.companyNumber() == null) {
            return Mono.just(ResponseEntity.badRequest().body("No criteria given"));
        }

        String criteria = searchRequest.companyNumber() != null
                ? searchRequest.companyNumber()
                : searchRequest.companyName();

        boolean includeInactiveCompanies = includeInactive != null ? includeInactive : false;

        return riskService.findCompanies(criteria, includeInactiveCompanies)
                .map(ResponseEntity::ok);
    }
}
