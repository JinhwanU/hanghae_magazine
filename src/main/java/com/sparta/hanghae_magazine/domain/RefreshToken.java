package com.sparta.hanghae_magazine.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Id
    @Column(nullable = false)
    private String refreshToken;
}
