package com.doha.practice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "exchange")
public class ExchangeRateApiProperties {
    private String url;
    private String api;
}
