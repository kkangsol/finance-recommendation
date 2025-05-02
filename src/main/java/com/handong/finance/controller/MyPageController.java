package com.handong.finance.controller;

import com.handong.finance.domain.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        if(user == null){
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "mypage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}
