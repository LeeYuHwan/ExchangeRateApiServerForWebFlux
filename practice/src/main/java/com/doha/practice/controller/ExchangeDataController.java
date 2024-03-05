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

    @GetMapping("/web-client")
    public Mono<?> createExchangeRate(){
        return apiServiceProvider.executeApi("v1.exchange.web-client", null);
    }

    @GetMapping("/data")
    public Mono<?> getDataExchangeRate(){
        return apiServiceProvider.executeApi("v1.exchange.data", null);
    }

}
