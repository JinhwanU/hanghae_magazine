package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.dto.LoginRequestDto;
import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
import com.sparta.hanghae_magazine.dto.ResponseTokenDto;
import com.sparta.hanghae_magazine.model.Success;
import com.sparta.hanghae_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Success> registerUser(@Valid @RequestBody RegisterRequestDto requestDto, Errors errors) {
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }

        if (requestDto.passwordCheck(requestDto.getPassword(), requestDto.getUsername())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "비밀번호 내에 아이디를 포함할 수 없습니다.");
        } else if (!requestDto.isPasswordEquals(requestDto.getPassword(), requestDto.getPasswordCheck())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호확인이 일치하지 않습니다.");
        } else {
            userService.registerUser(requestDto);
            return new ResponseEntity<>(new Success(true, "회원가입 성공"), HttpStatus.OK);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<Success> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
//        if (user != null){
//            return new ResponseEntity<>(new Success(false, "이미 로그인 중입니다."), HttpStatus.BAD_REQUEST);
//        }
        ResponseTokenDto token = userService.login(requestDto);

        response.setHeader("ACCESS_TOKEN", token.getACCESS_TOKEN());
        response.setHeader("REFRESH_TOKEN", token.getREFRESH_TOKEN());

        return new ResponseEntity<>(new Success(true, "로그인에 성공했습니다."), HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<Success> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>(new Success(true, "로그아웃 성공"), HttpStatus.OK);
    }
}
