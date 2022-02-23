package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.model.Posts;
import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.dto.PostResponseDto;
import com.sparta.hanghae_magazine.model.Users;
import com.sparta.hanghae_magazine.repository.LikeRepository;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
                () -> new NullPointerException("해당 postId가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public Long save(PostRequestDto requestDto, String username) {
        Users result = userRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
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
    public Long modify(Long postId, PostRequestDto requestDto, String username) {
        Posts post = postRepository.findByPostId(postId).orElseThrow(
                () -> new NullPointerException("해당 아이디가 없습니다.")
        );
        if (post.getUser().getUsername().equals(username)) {
            post.update(requestDto);
            return postId;
        } else {
            throw new IllegalArgumentException("username이 일치하지 않습니다.");
        }
    }
}
