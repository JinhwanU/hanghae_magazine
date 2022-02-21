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
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/user/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUser(@Valid RegisterRequestDto requestDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
//            model.addAttribute("requestDto", requestDto);
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "register";
        }
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

//    @PostMapping("/user/login")
//    public String loginUser() {
//        userService.loginUser();
//        return "redirect:/";
//    }
}
