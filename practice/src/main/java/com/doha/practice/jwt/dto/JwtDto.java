package com.doha.practice.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtDto {
    private int resultCode;
    private String resultMsg;
    private String token;
}
