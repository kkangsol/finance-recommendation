package com.handong.finance.controller;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.Category;
import com.handong.finance.domain.userform.UserForm;
import com.handong.finance.dto.UserFormRequest;
import com.handong.finance.service.UserFormService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserFormController {

    private final UserFormService userFormService;

    @GetMapping("/user-form")
    public String userForm(HttpSession session, RedirectAttributes redirectAttributes){
        //RedirectAttributes : model과 다르게 redirect시 일회성으로 전달(flash)
        if(session.getAttribute("loginUser") == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요한 서비스입니다");
            return "redirect:/login";
        }
        return "/user-form";
    }

    @PostMapping("/user-form")
    public String handleForm(@ModelAttribute UserFormRequest request, HttpSession session, RedirectAttributes redirectAttributes){
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser == null) {
            redirectAttributes.addFlashAttribute("message", "로그인이 필요한 서비스입니다");
            return "redirect:/login";
        }

        UserForm userForm = request.toEntity(loginUser);
        userFormService.saveUserForm(userForm);

        if(request.getCategory() == Category.DEPOSIT){
            return "redirect:/recommend/deposit";
        }else if(request.getCategory() == Category.LOAN){
            return "redirect:/recommend/loan";
        }else{
            System.out.println("잘못된 카테고리 선택입니다.");
            return "redirect:/user-form";
        }

    }

}
