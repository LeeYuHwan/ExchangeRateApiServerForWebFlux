package com.doha.practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ExchangeApiResponseDto {

    private Rates rates;
    @JsonProperty("time_last_update_utc")
    private String timeLastUpdateUtc;
    @Data
    public static class Rates{
        @JsonProperty("USD")
        private String usd;
        @JsonProperty("KRW")
        private String krw;
    }

}
