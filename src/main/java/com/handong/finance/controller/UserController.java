package com.handong.finance.controller;


import com.handong.finance.dto.UserSignupRequest;
import com.handong.finance.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(UserSignupRequest request) {
        userService.saveUser(request);
        return "/signup-success";
    }
}
