package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import com.sparta.hanghae_magazine.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService likeService;

    @PostMapping("/api/post/like")
    public Long saveLike(@RequestBody LikeRequestDto requestDto) {
        return likeService.saveLike(requestDto);
    }
}
