package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.domain.Posts;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {

    @EntityGraph(attributePaths = {"likeList"})
    @Query("select DISTINCT p from Posts p order by p.createdAt desc")
    List<Posts> findAll();

    void deleteByPostId(Long postId);

    Optional<Posts> findByPostId(Long postId);
}
