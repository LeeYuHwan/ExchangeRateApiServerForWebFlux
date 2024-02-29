package com.doha.practice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExchangeDto {
    private String usd;
    private String krw;
    private String timeLastUpdateUtc;
}
