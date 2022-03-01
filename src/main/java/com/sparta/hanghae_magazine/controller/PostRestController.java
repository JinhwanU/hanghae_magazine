package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.model.Success;
import com.sparta.hanghae_magazine.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostRestController {
    private final PostService postService;

    @GetMapping("/api/post")
    public List<PostResponseDto> findPostAll(@AuthenticationPrincipal Users users) {
        if (users == null) {
            return postService.findAll();
        } else {
            return postService.findAll(users.getUsername());
        }
    }

    @GetMapping("/api/post/{postId}")
    public PostResponseDto findPost(@PathVariable Long postId, @AuthenticationPrincipal Users users) {
        if (users == null) {
            return postService.findOne(postId);
        } else {
            return postService.findOne(postId, users.getUsername());
        }
    }

    @PostMapping("/api/post")
    public ResponseEntity<Success> savePost(@RequestBody @Valid PostRequestDto requestDto, @AuthenticationPrincipal Users users, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }
        postService.save(requestDto, users.getUsername());
        return new ResponseEntity<>(new Success(true, "게시글 저장 성공"), HttpStatus.OK);
    }

    @DeleteMapping("/api/post/{postId}")
    public ResponseEntity<Success> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return new ResponseEntity<>(new Success(true, "게시글 삭제 성공"), HttpStatus.OK);
    }

    @PutMapping("/api/post/{postId}")
    public ResponseEntity<Success> modifyPost(@PathVariable Long postId, @RequestBody @Valid PostRequestDto requestDto, @AuthenticationPrincipal Users users, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }
        postService.modify(postId, requestDto, users.getUsername());
        return new ResponseEntity<>(new Success(true, "게시글 수정 성공"), HttpStatus.OK);
    }
}
