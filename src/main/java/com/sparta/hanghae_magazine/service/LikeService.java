package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import com.sparta.hanghae_magazine.model.Likes;
import com.sparta.hanghae_magazine.model.Posts;
import com.sparta.hanghae_magazine.model.Users;
import com.sparta.hanghae_magazine.repository.LikeRepository;
import com.sparta.hanghae_magazine.repository.PostRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public void saveLike(LikeRequestDto requestDto, String username) {

        Posts findPost = postRepository.findByPostId(requestDto.getPostId()).orElseThrow(
                () -> new NullPointerException("해당 postId가 존재하지 않습니다.")
        );
        Users findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("해당 username이 존재하지 않습니다")
        );
        Optional<Likes> findLike = likeRepository.findLikeByPost_PostIdAndUser_Username(requestDto.getPostId(), username);
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
