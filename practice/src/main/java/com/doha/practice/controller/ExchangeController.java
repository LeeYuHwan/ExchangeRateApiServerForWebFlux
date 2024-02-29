package com.doha.practice.controller;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeApiResponseDto;
import com.doha.practice.mapper.ExchangeMapper;
import com.doha.practice.service.ExchangeService;
import com.doha.practice.webclient.ExchangeWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/exchange")
public class ExchangeController {

    private final ExchangeWebClient exchangeWebClient;
    private final ExchangeMapper mapper;
    private final ExchangeService exchangeService;

    @GetMapping
    public Mono<ExchangeRate> createExchangeRate(){

        log.info("CREATE EXCHANGE RATE START");
        Mono<ExchangeApiResponseDto> exchangeApiResponseDto = exchangeWebClient.streamingExchangeRates();

        return exchangeApiResponseDto.flatMap(data -> exchangeService.createExchangeRate(setExchangeDomain(data)));
    }

    private ExchangeRate setExchangeDomain(ExchangeApiResponseDto responseDtoMono) {

        ExchangeRate exchangeDomain = new ExchangeRate();
        exchangeDomain.setUsd(responseDtoMono.getRates().getUsd());
        exchangeDomain.setKrw(responseDtoMono.getRates().getKrw());
        exchangeDomain.setTimeLastUpdateUtc(responseDtoMono.getTimeLastUpdateUtc());
        
        return exchangeDomain;
    }

}
