package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.model.Success;
import com.sparta.hanghae_magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PostRestController {
    private final PostService postService;

    @GetMapping("/api/post")
    public List<PostResponseDto> findPostAll() {
        return postService.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/post/{postId}")
    public PostResponseDto findPost(@PathVariable Long postId) {
        return postService.findOne(postId);
    }

    @PostMapping("/api/post")
    public ResponseEntity<Success> savePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal Users users) {
        postService.save(requestDto, users.getUsername());
        return new ResponseEntity<>(new Success(true, "게시글 저장 성공"), HttpStatus.OK);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<Success> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return new ResponseEntity<>(new Success(true, "게시글 삭제 성공"), HttpStatus.OK);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<Success> modifyPost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal Users users) {
        postService.modify(postId, requestDto, users.getUsername());
        return new ResponseEntity<>(new Success(true, "게시글 수정 성공"), HttpStatus.OK);
    }
}
