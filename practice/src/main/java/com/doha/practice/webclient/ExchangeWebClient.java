package com.doha.practice.webclient;

import com.doha.practice.dto.ExchangeApiResponseDto;
import com.doha.practice.properties.ExchangeRateApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ExchangeWebClient {

    private final ExchangeRateApiProperties exchangeRateApiProperties;

    @Bean
    public ApplicationRunner streamingExchangeRates() {
        return (ApplicationArguments arguments) -> {
            WebClient webClient = WebClient.create(exchangeRateApiProperties.getUrl());

            Mono<ExchangeApiResponseDto> response =
                    webClient
                            .get()
                            .uri(exchangeRateApiProperties.getApi())
                            .retrieve()
                            .bodyToMono(ExchangeApiResponseDto.class);

            response.subscribe(exchange -> {
                        log.info("usd: {}", exchange.getRates().getUsd());
                        log.info("krw: {}", exchange.getRates().getKrw());
                        log.info("lastUpdateTime: {}", exchange.getTimeLastUpdateUtc());
                        log.info("=======================================");
                    },
                    error -> log.error("# error happened: ", error));
        };
    }
}
