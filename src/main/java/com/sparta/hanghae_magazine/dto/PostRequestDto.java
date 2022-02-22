package com.sparta.hanghae_magazine.dto;

import com.sparta.hanghae_magazine.model.Posts;
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

    public Posts toEntity() {
        return Posts.builder()
                .image(image)
                .contents(contents)
                .build();
    }
}
