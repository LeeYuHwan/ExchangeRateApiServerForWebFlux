package com.doha.practice.common;

import com.doha.practice.common.util.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiServiceProvider {

    public Mono<Object> executeApi(String apiId, Object requestDto, Mono<Object> responseDto){

        log.info("API REQUEST :: " + apiId);

        ApiService execute = (ApiService) BeanUtils.getBean(apiId);
        responseDto = execute.perform(requestDto, responseDto);

        log.info("API PROCESSED COMPLETE : RESPONSE DTO :: {}", responseDto);

        return responseDto;

    }

}
