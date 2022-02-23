package com.sparta.hanghae_magazine.dto;

import com.sparta.hanghae_magazine.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "값이 존재하지 않습니다:image")
    private String image;
    @NotEmpty(message = "값이 존재하지 않습니다:contents")
    private String contents;

    public Posts toEntity() {
        return Posts.builder()
                .image(image)
                .contents(contents)
                .build();
    }
}
