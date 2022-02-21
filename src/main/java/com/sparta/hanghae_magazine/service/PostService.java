package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.model.Post;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public PostResponseDto findOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public Long save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getPostId();
    }

    @Transactional
    public Long delete(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    @Transactional
    public Long modify(Long postId, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        post.update(requestDto);
        return postId;
    }

}
