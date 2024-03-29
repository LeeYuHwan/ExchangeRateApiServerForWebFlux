package com.doha.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeDto {
    private int resultCode;
    private String resultMsg;
    private String usd;
    private String krw;
    private String timeLastUpdateUtc;
}
