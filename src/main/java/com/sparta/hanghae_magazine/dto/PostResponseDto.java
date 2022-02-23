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

    public PostResponseDto(Posts post) {
        this.postId = post.getPostId();
        this.image = post.getImage();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.likeCount = (long) post.getLikeList().size();
    }
}
