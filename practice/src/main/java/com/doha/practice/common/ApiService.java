package com.doha.practice.common;

import com.doha.practice.domain.ExchangeRate;
import reactor.core.publisher.Mono;

public interface ApiService {
    public Mono<Object> perform(Object requestDto, Mono<Object> responseDto);
}

