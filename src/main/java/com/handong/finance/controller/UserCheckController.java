package com.handong.finance.controller;

import com.handong.finance.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserCheckController {
    private final UserRepository userRepository;

    @PostMapping("/api/check-username")
    public ResponseEntity<Map<String, Boolean>> userCheck(@RequestBody Map<String, String> request){
        String username = request.get("username");
        boolean isAvailable = !(userRepository.existsByUsername(username));
        return ResponseEntity.ok(Map.of("isAvailable",isAvailable));
    }

    @PostMapping("/api/check-phoneNumber")
    public ResponseEntity<Map<String, Boolean>> phoneNumberCheck(@RequestBody Map<String, String> request){
        String phoneNumber = request.get("phoneNumber");
        boolean isAvailable = !(userRepository.existsByPhoneNumber(phoneNumber));
        return ResponseEntity.ok(Map.of("isAvailable", isAvailable));
    }
}
