package com.sparta.hanghae_magazine.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequestDto {
    private Long postId;
    private String username;

    public LikeRequestDto(Long postId, String username) {
        this.postId = postId;
        this.username = username;
    }
}
