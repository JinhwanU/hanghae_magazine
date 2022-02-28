package com.sparta.hanghae_magazine.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDto {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;
}