package com.handong.finance.controller;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")   //로그인 폼 보여줌
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model){
        if(error != null){
            model.addAttribute("error","❌ 로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
        }   // 비밀번호가 일치하지 않아 로그인 리다이렉트 됐는데 error값이 있는채로 들어온다면 알림창 띄워주고 로그인 폼 다시 반환
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession){
        User user = userRepository.findByUsername(username);
        if(user == null || !(bCryptPasswordEncoder.matches(password, user.getPassword()))){
            return "redirect:/login?error=true";
        }
        httpSession.setAttribute("loginUser", user);
        return "redirect:/mypage";
    }
}
