package com.sparta.hanghae_magazine.model;

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

//    public void setPost(Post post) {
//        this.post = post;
//        post.getLikeList().add(this);
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//        user.getLikeList().add(this);
//    }
}
