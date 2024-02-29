package com.doha.practice.webclient;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeApiResponseDto;
import com.doha.practice.dto.ExchangeDto;
import com.doha.practice.properties.ExchangeRateApiProperties;
import com.doha.practice.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExchangeWebClient {

    private final ExchangeRateApiProperties exchangeRateApiProperties;

    public Mono<ExchangeApiResponseDto> streamingExchangeRates() {

        WebClient webClient = WebClient.create(exchangeRateApiProperties.getUrl());

        Mono<ExchangeApiResponseDto> response =
                webClient
                        .get()
                        .uri(exchangeRateApiProperties.getApi())
                        .retrieve()
                        .bodyToMono(ExchangeApiResponseDto.class);

        log.info("---------TODAY EXCHANGE RATE---------");

        response.subscribe(exchange -> {
                    log.info("usd: {}", exchange.getRates().getUsd());
                    log.info("krw: {}", exchange.getRates().getKrw());
                    log.info("lastUpdateTime: {}", exchange.getTimeLastUpdateUtc());
                    log.info("=======================================");
                },
                error -> log.error("# error happened: ", error));

        log.info("--------------------------------------");

        return response;
    }
}
