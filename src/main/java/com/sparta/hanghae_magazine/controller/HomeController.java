package com.sparta.hanghae_magazine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("username");
        return "index";
    }

//    @GetMapping("/user/login")
//    public String showLoginForm() {
//        if (principal != null) {
//            throw new RestException(HttpStatus.BAD_REQUEST, "이미 로그인 되어있습니다.");
//        }
//        return "login";
//    }

//    @GetMapping("/user/register")
//    public String showRegisterForm() {
//        if (principal != null) {
//            throw new RestException(HttpStatus.BAD_REQUEST, "이미 로그인 되어있습니다.");
//        }
//        return "register";
//    }

//    @PostMapping("/user/logout")
//    public String logout(){
//        return "login";
//    }
}