package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.domain.RefreshToken;
import com.sparta.hanghae_magazine.dto.LoginRequestDto;
import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.dto.TokenResponseDto;
import com.sparta.hanghae_magazine.repository.TokenRepository;
import com.sparta.hanghae_magazine.repository.UserRepository;
import com.sparta.hanghae_magazine.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    @Transactional
    public void registerUser(RegisterRequestDto requestDto) {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "이미 존재하는 username입니다.");
        }
        userRepository.save(Users.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .realName(requestDto.getRealName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto requestDto) {
        Users user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
        }
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername(), user.getRoles());
        tokenRepository.save(new RefreshToken(refreshToken));

        return TokenResponseDto.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }

    @Transactional
    public void logout(HttpServletRequest request){
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        tokenRepository.deleteByRefreshToken(refreshToken);
        SecurityContextHolder.clearContext();
    }
}






