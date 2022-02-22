package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByOrderByCreatedAtDesc();

    void deleteByPostId(Long postId);

    Optional<Posts> findByPostId(Long postId);
}
