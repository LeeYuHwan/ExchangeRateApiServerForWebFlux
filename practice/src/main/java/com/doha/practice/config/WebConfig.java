package com.doha.practice.config;

import com.doha.practice.converter.ExchangeApiDtoToExchangeDomainConverter;
import com.doha.practice.converter.ExchangeDomainToExchangeDtoConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;


@Configuration
public class WebConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DefaultConversionService conversionService(){

        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new ExchangeApiDtoToExchangeDomainConverter());
        conversionService.addConverter(new ExchangeDomainToExchangeDtoConverter());

        return conversionService;
    }
}
