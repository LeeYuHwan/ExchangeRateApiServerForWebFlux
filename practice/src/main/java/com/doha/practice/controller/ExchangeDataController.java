package com.doha.practice.controller;

import com.doha.practice.common.ApiServiceProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/exchange")
public class ExchangeDataController {

    private final ApiServiceProvider apiServiceProvider;

    @GetMapping
    public Mono<Object> createExchangeRate(){
        return apiServiceProvider.executeApi("exchange.create-data.v1", null, null);
    }

    @GetMapping("/data")
    public Mono<Object> getDataExchangeRate(){
        return apiServiceProvider.executeApi("exchange.get-data.v1", null, null);
    }

}
