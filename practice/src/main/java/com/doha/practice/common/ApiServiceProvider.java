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

    public Mono<?> executeApi(String apiId, Object requestDto){

        log.info("API REQUEST :: " + apiId);

        ApiService execute = (ApiService) BeanUtils.getBean(apiId);
        Mono<?> response = execute.perform(requestDto);

        log.info("API PROCESSED COMPLETE : RESPONSE DTO :: {}", response);

        return response;

    }

}
