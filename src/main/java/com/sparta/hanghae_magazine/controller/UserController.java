package com.sparta.hanghae_magazine.controller;

import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
import com.sparta.hanghae_magazine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String showLoginForm(Principal principal) {
        if (principal != null) {
            throw new IllegalStateException("이미 로그인이 되어있습니다.");
        }
        return "login";
    }

    @GetMapping("/user/register")
    public String showRegisterForm(Principal principal) {
        if (principal != null) {
            throw new IllegalStateException("이미 로그인이 되어있습니다.");
        }
        return "register";
    }

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
        } else {
            userService.registerUser(requestDto);
            return "redirect:/user/login";
        }
    }
}
