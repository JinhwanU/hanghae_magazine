package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import com.sparta.hanghae_magazine.domain.Likes;
import com.sparta.hanghae_magazine.domain.Posts;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.repository.LikeRepository;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void saveLike(LikeRequestDto requestDto) {

        Posts findPost = postRepository.findByPostId(requestDto.getPostId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 postId가 존재하지 않습니다.")
        );
        Users findUser = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "해당 username이 존재하지 않습니다.")
        );
        Optional<Likes> findLike = likeRepository.findLikeByPost_PostIdAndUser_Username(requestDto.getPostId(), requestDto.getUsername());
        if (findLike.isPresent()) {
            likeRepository.deleteById(findLike.get().getLikeId());
        } else {
            Likes like = Likes.builder()
                    .user(findUser)
                    .post(findPost)
                    .build();
            likeRepository.save(like);
            findPost.addLike(like);
            findUser.addLike(like);
        }


    }
}
