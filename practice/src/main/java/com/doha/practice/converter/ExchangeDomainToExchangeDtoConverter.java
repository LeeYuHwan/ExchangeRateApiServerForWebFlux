package com.doha.practice.converter;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeDomainToExchangeDtoConverter implements Converter<ExchangeRate, ExchangeDto> {
    @Override
    public ExchangeDto convert(ExchangeRate source) {
        return ExchangeDto.builder()
                .usd(source.getUsd())
                .krw(source.getKrw())
                .timeLastUpdateUtc(source.getTimeLastUpdateUtc())
                .build();
    }
}
