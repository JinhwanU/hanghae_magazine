package com.sparta.hanghae_magazine.dto;

import com.sparta.hanghae_magazine.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String image;
    private String contents;
    private String username;
    private Long likeCount;
    private boolean isLiked;

    public PostResponseDto(Posts posts) {
        this.postId = posts.getPostId();
        this.image = posts.getImage();
        this.contents = posts.getContents();
        this.username = posts.getUser().getUsername();
        this.likeCount = (long) posts.getLikeList().size();
        this.isLiked = false;
    }

    public PostResponseDto(Posts posts, boolean isLiked) {
        this.postId = posts.getPostId();
        this.image = posts.getImage();
        this.contents = posts.getContents();
        this.username = posts.getUser().getUsername();
        this.likeCount = (long) posts.getLikeList().size();
        this.isLiked = isLiked;
    }

}
