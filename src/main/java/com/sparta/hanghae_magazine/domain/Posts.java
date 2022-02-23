package com.sparta.hanghae_magazine.domain;

import com.sparta.hanghae_magazine.dto.PostRequestDto;
import com.sparta.hanghae_magazine.utility.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Posts extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likeList = new ArrayList<>();

    public void addLike(Likes like) {
        this.likeList.add(like);
        like.setPost(this);
    }

    @Builder
    public Posts(String image, String contents) {
        this.image = image;
        this.contents = contents;
    }

    public void update(PostRequestDto requestDto) {
        this.image = requestDto.getImage();
        this.contents = requestDto.getContents();
    }
}
