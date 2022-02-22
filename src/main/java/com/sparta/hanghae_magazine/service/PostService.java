package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.model.Posts;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.model.Users;
import com.sparta.hanghae_magazine.repository.LikeRepository;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public List<Posts> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public PostResponseDto findOne(Long postId) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public Long save(PostRequestDto requestDto) {
//        return postRepository.save(requestDto.toEntity()).getPostId();
        Optional<Users> result = Optional.ofNullable(userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        ));
        Posts post = Posts.builder()
                .contents(requestDto.getContents())
                .image(requestDto.getImage())
                .build();
        postRepository.save(post);
        result.get().addPost(post);
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
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        post.update(requestDto);
        return postId;
    }

}
