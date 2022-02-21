package com.sparta.hanghae_magazine.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikePK implements Serializable {
    private Long postId;
    private String username;
}
