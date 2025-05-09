package com.handong.finance.controller;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.UserForm;
import com.handong.finance.domain.userform.UserFormRepository;
import com.handong.finance.dto.DepositProduct;
import com.handong.finance.service.RecommendService;
import com.handong.finance.service.UserFormService;
import com.handong.finance.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class RecommendController {

    private final UserFormService userFormService;
    private final RecommendService recommendService;

   @GetMapping("/recommend/deposit")
    public String recommendDeposit(HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        UserForm userForm = userFormService.getUserForm(loginUser).orElseThrow(() -> new IllegalArgumentException("입력값이 없습니다."));
        List<DepositProduct> depositProductList =recommendService.recommendDepositProducts(userForm);
        model.addAttribute("depositProductList",depositProductList);
        return("/recommend/deposit");
    }
}
