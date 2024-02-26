package com.doha.practice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Exchange {

    @Id
    private long exchangeId;
    private String usd;
    private String krw;
    private LocalDateTime timeLastUpdateUtc;

}
