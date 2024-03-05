package com.doha.practice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
public class UserInfo {
    @Id
    private long userId;
    private String userName;
    private String userKey;
    private LocalDateTime createTime;
}
