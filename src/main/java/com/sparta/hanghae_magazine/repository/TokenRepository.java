package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshToken(String token);

    void deleteByRefreshToken(String refreshToken);

}
