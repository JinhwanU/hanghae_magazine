package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

//    Post findByPostId(@Param("postId") Long postId);
}
