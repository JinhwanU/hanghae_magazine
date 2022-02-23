package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import com.sparta.hanghae_magazine.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService likeService;

    @PostMapping("/api/post/like")
    public void saveLike(@RequestBody LikeRequestDto requestDto, Principal principal) {
        if (principal == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        likeService.saveLike(requestDto, principal.getName());
    }
}
