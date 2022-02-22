package com.sparta.hanghae_magazine.model;

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
public class User {

    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String realName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likeList = new ArrayList<>();

    public void addPost(Post post) {
        this.postList.add(post);
        post.setUser(this);
    }

    public void addLike(Like like) {
        this.likeList.add(like);
        like.setUser(this);
    }

    @Builder
    public User(String username, String password, String realName) {
        this.username = username;
        this.password = password;
        this.realName = realName;
    }
}
