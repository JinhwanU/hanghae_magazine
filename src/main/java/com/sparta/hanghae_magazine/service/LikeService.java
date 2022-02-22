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

//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//    private final LikeRepository likeRepository;
//
//    @Transactional
//    public Long saveLike(LikeRequestDto requestDto) {
//
//        Optional<Post> findPost = Optional.ofNullable(postRepository.findByPostId(requestDto.getPostId()).orElseThrow(
//                () -> new NullPointerException("해당 postId가 존재하지 않습니다.")
//        ));
//        Optional<User> findUser = Optional.ofNullable(userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
//                () -> new NullPointerException("해당 username이 존재하지 않습니다")
//        ));
//        Optional<Like> findLike = likeRepository.findLikeByPost_PostIdAndUser_Username(requestDto.getPostId(), requestDto.getUsername());
//        if (findLike.isPresent()) {
//            throw new IllegalStateException("이미 좋아요 한 상태입니다");
//        } else {
//            Like like = Like.builder()
//                    .user(findPost.get().getUser())
//                    .post(findPost.get())
//                    .build();
//            likeRepository.save(like);
//            findPost.get().addLike(like);
//            findUser.get().addLike(like);
//            return like.getLikeId();
//        }
//    }

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public Long saveLike(LikeRequestDto requestDto) {

        Optional<Posts> findPost = Optional.ofNullable(postRepository.findByPostId(requestDto.getPostId()).orElseThrow(
                () -> new NullPointerException("해당 postId가 존재하지 않습니다.")
        ));
        Optional<Users> findUser = Optional.ofNullable(userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new NullPointerException("해당 username이 존재하지 않습니다")
        ));
        Optional<Likes> findLike = likeRepository.findLikeByPost_PostIdAndUser_Username(requestDto.getPostId(), requestDto.getUsername());

        if (findLike.isPresent()) {
            throw new IllegalStateException("이미 좋아요 한 상태입니다");
        } else {
            Likes like = Likes.builder()
                    .user(findUser.get())
                    .post(findPost.get())
                    .build();
            likeRepository.save(like);
            findPost.get().addLike(like);
            findUser.get().addLike(like);
            return like.getLikeId();
        }
    }
}
