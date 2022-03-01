package com.sparta.hanghae_magazine.dto;

import com.sparta.hanghae_magazine.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String image;
    private String contents;
    private String username;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isLiked;

    public PostResponseDto(Posts posts) {
        this.postId = posts.getPostId();
        this.image = posts.getImage();
        this.contents = posts.getContents();
        this.username = posts.getUser().getUsername();
        this.likeCount = (long) posts.getLikeList().size();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
        this.isLiked = false;
    }

    public PostResponseDto(Posts posts, boolean isLiked) {
        this.postId = posts.getPostId();
        this.image = posts.getImage();
        this.contents = posts.getContents();
        this.username = posts.getUser().getUsername();
        this.likeCount = (long) posts.getLikeList().size();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
        this.isLiked = isLiked;
    }

}
