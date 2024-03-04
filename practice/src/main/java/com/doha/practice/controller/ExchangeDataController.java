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
        return apiServiceProvider.executeApi("exchange.web-client.v1", null);
    }

    @GetMapping("/data")
    public Mono<?> getDataExchangeRate(){
        return apiServiceProvider.executeApi("exchange.data.v1", null);
    }

}
