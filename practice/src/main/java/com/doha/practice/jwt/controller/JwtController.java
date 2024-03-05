package com.doha.practice.jwt.controller;

import com.doha.practice.common.ApiServiceProvider;
import com.doha.practice.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/jwt")
public class JwtController {

    private final ApiServiceProvider apiServiceProvider;

    @PostMapping("/token")
    public Mono<?> createJWTToken(@RequestBody UserInfoDto userInfoDto){
        return apiServiceProvider.executeApi("v1.jwt.token", userInfoDto);
    }



}
