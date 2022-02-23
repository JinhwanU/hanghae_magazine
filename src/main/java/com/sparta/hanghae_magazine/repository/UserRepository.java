package com.sparta.hanghae_magazine.repository;

import com.sparta.hanghae_magazine.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUsername(String username);

    void deleteByUsername(String username);

}
