package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.domain.Posts;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Posts> findAll() {
        return postRepository.findAll();
    }

    @Transactional
    public PostResponseDto findOne(Long postId) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND,"해당 postId가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public Long save(PostRequestDto requestDto) {
        Users result = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 username이 존재하지 않습니다.")
        );
        Posts post = Posts.builder()
                .contents(requestDto.getContents())
                .image(requestDto.getImage())
                .build();
        postRepository.save(post);
        result.addPost(post);
        return requestDto.toEntity().getPostId();
    }

    @Transactional
    public Long delete(Long postId) {
        postRepository.deleteByPostId(postId);
        return postId;
    }

    @Transactional
    public Long modify(Long postId, PostRequestDto requestDto) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND,"해당 postId가 존재하지 않습니다.")
        );
        if (post.getUser().getUsername().equals(requestDto.getUsername())) {
            post.update(requestDto);
            return postId;
        } else {
            throw new RestException(HttpStatus.BAD_REQUEST, "username이 일치하지 않습니다.");
        }
    }
}
