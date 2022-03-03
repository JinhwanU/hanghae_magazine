package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findAll(Pageable pageable);

    void deleteByPostId(Long postId);

    Optional<Posts> findByPostId(Long postId);
}
