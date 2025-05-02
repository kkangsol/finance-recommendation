package com.handong.finance.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserFormController {

    @GetMapping("/user-form")
    public String userForm(HttpSession session, RedirectAttributes redirectAttributes){
        //RedirectAttributes : model과 다르게 redirect시 일회성으로 전달(flash)
        if(session.getAttribute("loginUser") == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요한 서비스입니다");
            return "redirect:/login";
        }
        return "/user-form";
    }
}
