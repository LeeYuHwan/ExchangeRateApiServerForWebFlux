package com.doha.practice.service;

import com.doha.practice.common.ApiService;
import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeDto;
import com.doha.practice.dto.ExchangeErrorDto;
import com.doha.practice.exception.DataException;
import com.doha.practice.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("exchange.data.v1")
public class ExchangeDataService implements ApiService {

    private final ExchangeRepository exchangeRepository;
    private final DefaultConversionService conversionService;

    @Override
    public Mono<?> perform(Object requestDto) {
        return getLatestExchangeRate()
                .map(data -> conversionService.convert(data, ExchangeDto.class));
    }

    private Mono<ExchangeRate> getLatestExchangeRate() {
        return exchangeRepository.findFirstByOrderByCreateTimeDesc()
                .switchIfEmpty(Mono.error((new DataException(9999, "DATA NOT EXIST"))));
    }
    private ExchangeErrorDto setExchangeErrorDto(int resultCode, String resultMsg) {
        return ExchangeErrorDto.builder()
                .resultCode(resultCode)
                .resultMsg(resultMsg)
                .build();
    }

}
