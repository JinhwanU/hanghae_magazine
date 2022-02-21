package com.sparta.hanghae_magazine.dto;

import com.sparta.hanghae_magazine.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    private String username;
    private String image;
    private String contents;

    public PostRequestDto(String username, String image, String contents) {
        this.username = username;
        this.image = image;
        this.contents = contents;
    }

    public Post toEntity() {
        return Post.builder()
//                .username(username)
                .image(image)
                .contents(contents)
                .build();
    }
}
