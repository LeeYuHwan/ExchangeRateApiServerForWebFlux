package com.doha.practice.service;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    public Mono<ExchangeRate> createExchangeRate(ExchangeRate exchange) {
        return exchangeRepository.save(exchange);
    }

}
