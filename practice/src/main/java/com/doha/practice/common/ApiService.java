package com.doha.practice.common;

import com.doha.practice.domain.ExchangeRate;
import reactor.core.publisher.Mono;

public interface ApiService {
    public Mono<?> perform(Object requestDto);
}

