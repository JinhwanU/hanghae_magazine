package com.sparta.hanghae_magazine.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@IdClass(LikePK.class)
public class Like {

    @Id
    @Column(name = "postId")
    private Long postId;

    @Id
    @Column(name = "username")
    private String username;
}
