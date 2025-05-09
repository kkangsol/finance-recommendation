package com.handong.finance.service;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.user.UserRepository;
import com.handong.finance.dto.UserSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(UserSignupRequest request){
        final String encodedPassword = passwordEncoder.encode(request.getPassword());

        userRepository.save(new User(
                request.getUsername(),
                encodedPassword,
                request.getName(),
                request.getPhoneNumber()
        ));
    }
}
