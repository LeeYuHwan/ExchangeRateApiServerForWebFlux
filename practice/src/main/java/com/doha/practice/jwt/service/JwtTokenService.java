package com.doha.practice.jwt.service;

import com.doha.practice.common.ApiService;
import com.doha.practice.domain.ExchangeRate;
import com.doha.practice.domain.UserInfo;
import com.doha.practice.dto.UserInfoDto;
import com.doha.practice.exception.DataException;
import com.doha.practice.jwt.JwtProvider;
import com.doha.practice.jwt.dto.JwtDto;
import com.doha.practice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service("v1.jwt.token")
public class JwtTokenService implements ApiService {

    private final UserInfoRepository userInfoRepository;
    private final JwtProvider jwtProvider;

    @Override
    public Mono<?> perform(Object requestDto) {

        UserInfoDto userInfoDto = (UserInfoDto) requestDto;

        log.info("SEARCHING :: USER NAME : " + userInfoDto.getUserName() + ", USER KEY : " + userInfoDto.getUserKey());

        return getUserInfo(userInfoDto.getUserName(), userInfoDto.getUserKey()).map(data ->
            JwtDto.builder()
                    .resultCode(0000)
                    .resultMsg("success")
                    .token(jwtProvider.createToken(data.getUserName()))
                    .build()
        );
    }

    private Mono<UserInfo> getUserInfo(String userName, String userKey) {
        return userInfoRepository.findByUserNameAndUserKey(userName, userKey)
                .switchIfEmpty(Mono.error((new DataException(7999, "NOT EXIST USER INFO"))));
    }
}
