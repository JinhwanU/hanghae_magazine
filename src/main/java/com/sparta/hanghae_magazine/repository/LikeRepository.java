package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
        Optional<Like> findLikeByPost_PostIdAndUser_Username(Long postId, String username);
}
