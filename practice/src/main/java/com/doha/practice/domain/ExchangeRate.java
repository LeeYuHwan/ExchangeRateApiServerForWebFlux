package com.doha.practice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ExchangeRate {

    @Id
    private long exchangeId;
    private String usd;
    private String krw;
    private String timeLastUpdateUtc;

}
