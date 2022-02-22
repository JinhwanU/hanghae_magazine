package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    void deleteByPostId(Long postId);

    Optional<Post> findByPostId(Long postId);
}
