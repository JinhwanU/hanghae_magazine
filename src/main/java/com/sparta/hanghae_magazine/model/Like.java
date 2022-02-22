package com.sparta.hanghae_magazine.model;

import com.sparta.hanghae_magazine.dto.LikeRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @Builder
    public Like(User user, Post post) {
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
