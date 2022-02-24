package com.sparta.hanghae_magazine.service;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
import com.sparta.hanghae_magazine.domain.Users;
import com.sparta.hanghae_magazine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void registerUser(RegisterRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String realName = requestDto.getRealName();

        Optional<Users> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST,"이미 존재하는 username입니다.");
        }

        Users user = new Users(username, password, realName);
        userRepository.save(user);
    }

    @Transactional
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            System.out.println(validKeyName);
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;

    }
}
