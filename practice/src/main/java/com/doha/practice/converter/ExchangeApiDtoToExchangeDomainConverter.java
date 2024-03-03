package com.doha.practice.converter;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeApiResponseDto;
import org.springframework.core.convert.converter.Converter;

public class ExchangeApiDtoToExchangeDomainConverter implements Converter<ExchangeApiResponseDto, ExchangeRate> {
    @Override
    public ExchangeRate convert(ExchangeApiResponseDto source) {
        return ExchangeRate.builder()
                .usd(source.getRates().getUsd())
                .krw(source.getRates().getKrw())
                .timeLastUpdateUtc(source.getTimeLastUpdateUtc())
                .build();
    }

}
