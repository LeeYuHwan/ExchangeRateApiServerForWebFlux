package com.doha.practice.service;

import com.doha.practice.common.ApiService;
import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeApiResponseDto;
import com.doha.practice.repository.ExchangeRepository;
import com.doha.practice.webclient.ExchangeWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("exchange.web-client.v1")
public class ExchangeWebClientService implements ApiService {

    private final ExchangeWebClient exchangeWebClient;
    private final DefaultConversionService conversionService;
    private final ExchangeRepository exchangeRepository;

    @Override
    public Mono<?> perform(Object requestDto) {
        Mono<ExchangeApiResponseDto> exchangeApiResponseDto = exchangeWebClient.streamingExchangeRates();
        return exchangeApiResponseDto.flatMap(data -> createExchangeRate(conversionService.convert(data, ExchangeRate.class)));
    }

    private Mono<ExchangeRate> createExchangeRate(ExchangeRate exchange) {
        return exchangeRepository.save(exchange);
    }

}
