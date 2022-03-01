package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.domain.Posts;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.repository.LikeRepository;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public List<PostResponseDto> findAll() {
        List<Posts> posts = postRepository.findAll();
        List<PostResponseDto> responseDto = new ArrayList<>();
        for (Posts post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            responseDto.add(postResponseDto);
        }
        return responseDto;
    }

    @Transactional
    public List<PostResponseDto> findAll(String username) {
        List<Posts> posts = postRepository.findAll();
        List<PostResponseDto> responseDto = new ArrayList<>();
        for (Posts post : posts) {
            boolean isLiked = likeRepository.existsByPost_PostIdAndUser_Username(post.getPostId(), username);
            PostResponseDto postResponseDto = new PostResponseDto(post, isLiked);
            responseDto.add(postResponseDto);
        }
        return responseDto;
    }

    @Transactional
    public PostResponseDto findOne(Long postId) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto findOne(Long postId, String username) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        boolean isLiked = likeRepository.existsByPost_PostIdAndUser_Username(postId, username);
        return new PostResponseDto(post, isLiked);
    }

    @Transactional
    public void save(PostRequestDto requestDto, String username) {
        Users result = userRepository.findByUsername(username).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 username이 존재하지 않습니다.")
        );
        Posts post = Posts.builder()
                .contents(requestDto.getContents())
                .image(requestDto.getImage())
                .build();
        postRepository.save(post);
        result.addPost(post);
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.deleteByPostId(postId);
    }

    @Transactional
    public void modify(Long postId, PostRequestDto requestDto, String username) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        if (post.getUser().getUsername().equals(username)) {
            post.update(requestDto);
        } else {
            throw new RestException(HttpStatus.BAD_REQUEST, "username이 일치하지 않습니다.");
        }
    }
}
