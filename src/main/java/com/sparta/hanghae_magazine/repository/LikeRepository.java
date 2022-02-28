package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findLikeByPost_PostIdAndUser_Username(Long postId, String username);

    boolean existsByPost_PostIdAndUser_Username(Long postId, String username);
}
