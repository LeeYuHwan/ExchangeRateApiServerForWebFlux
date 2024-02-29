package com.doha.practice.mapper;

import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.dto.ExchangeApiResponseDto;
import com.doha.practice.dto.ExchangeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExchangeMapper {

    ExchangeRate exchangeApiDtoToExchangeDto(ExchangeApiResponseDto exchangeApiResponseDtoDto);
    ExchangeRate exchangeDtoToExchangeDomain(ExchangeDto exchangeDto);
    ExchangeDto exchangeDomainToExchangeDto(ExchangeRate exchangeDto);

}
