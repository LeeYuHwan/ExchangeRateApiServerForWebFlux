package com.doha.practice.controller;

import com.doha.practice.properties.ExchangeRateApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeRateApiProperties exchangeRateApiProperties;

    @GetMapping("/test")
    public void test(){
        System.out.println(exchangeRateApiProperties.getUrl());
    }

}
