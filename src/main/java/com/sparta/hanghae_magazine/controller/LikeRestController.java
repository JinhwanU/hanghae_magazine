package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import com.sparta.hanghae_magazine.model.Success;
import com.sparta.hanghae_magazine.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService likeService;

    @PostMapping("/api/post/like")
    public ResponseEntity<Success> postLike(@RequestBody LikeRequestDto requestDto, @AuthenticationPrincipal Users users) {
//        if (principal == null) {
//            return new ResponseEntity<>(new Success(true, "로그인이 필요합니다"), HttpStatus.BAD_REQUEST);
//        }
        likeService.saveLike(requestDto, users.getUsername());
        return new ResponseEntity<>(new Success(true, "좋아요 성공"), HttpStatus.OK);
    }
}
