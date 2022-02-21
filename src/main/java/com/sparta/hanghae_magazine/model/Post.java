package com.sparta.hanghae_magazine.model;

import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.utility.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String contents;

//    @Column(nullable = false)
//    private String username;

    @Builder
    public Post(String image, String contents) {
        this.image = image;
        this.contents = contents;
    }

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;


    public Post(PostRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.contents = requestDto.getContents();
    }

//    public void addPost(User user){
//        this.user.
//
//    }
}
