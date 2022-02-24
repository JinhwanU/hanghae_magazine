package com.sparta.hanghae_magazine.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Posts post;

    @Builder
    public Likes(Users user, Posts post) {
        this.user = user;
        this.post = post;
    }

    public Likes(Posts post) {
        this.user = post.getUser();
        this.post = post;
    }
}
