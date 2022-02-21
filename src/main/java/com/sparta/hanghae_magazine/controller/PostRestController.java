package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.service.PostService;
import lombok.RequiredArgsConstructor;
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
    public Long savePost(@RequestBody PostRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @DeleteMapping("/api/post/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.delete(postId);
    }

    @PutMapping("/api/post/{postId}")
    public Long modifyPost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.modify(postId, requestDto);
    }
}
