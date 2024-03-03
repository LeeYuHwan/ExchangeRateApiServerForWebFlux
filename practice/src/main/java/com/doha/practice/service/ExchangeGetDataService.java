package com.doha.practice.service;

import com.doha.practice.common.ApiService;
import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeDto;
import com.doha.practice.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("exchange.get-data.v1")
public class ExchangeGetDataService implements ApiService {

    private final ExchangeRepository exchangeRepository;
    private final DefaultConversionService conversionService;

    @Override
    public Mono<Object> perform(Object requestDto, Mono<Object> responseDto) {
        return getLatestExchangeRate().map(data -> conversionService.convert(data, ExchangeDto.class));
    }

    private Mono<ExchangeRate> getLatestExchangeRate() {
        return exchangeRepository.findAll().last();
    }

}