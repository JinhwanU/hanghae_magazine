package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.advice.RestException;
import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
import com.sparta.hanghae_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String showLoginForm() {
//        if (principal != null) {
//            throw new RestException(HttpStatus.BAD_REQUEST, "이미 로그인 되어있습니다.");
//        }
        return "login";
    }

    @GetMapping("/user/register")
    public String showRegisterForm() {
//        if (principal != null) {
//            throw new RestException(HttpStatus.BAD_REQUEST, "이미 로그인 되어있습니다.");
//        }
        return "register";
    }
    //TODO : 에러메세지 JSON으로 반환하도록 변경 (라이브러리 찾아보면 나올수도 )
    @PostMapping("/user/register")
    public String registerUser(@Valid RegisterRequestDto requestDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "register";
        }
        if (requestDto.passwordCheck(requestDto.getPassword(), requestDto.getUsername())) {
            model.addAttribute("valid_password", "비밀번호 내에 아이디를 포함할 수 없습니다.");
            return "register";
        } else if (!requestDto.isPasswordEquals(requestDto.getPassword(), requestDto.getPasswordChk())) {
            model.addAttribute("valid_passwordChk", "비밀번호와 비밀번호확인이 일치하지 않습니다.");
            return "register";
        } else {
            userService.registerUser(requestDto);
            return "redirect:/user/login";
        }
    }
}
