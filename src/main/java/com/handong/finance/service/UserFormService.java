package com.handong.finance.service;

import com.handong.finance.domain.user.User;
import com.handong.finance.domain.userform.UserForm;
import com.handong.finance.domain.userform.UserFormRepository;
import com.handong.finance.dto.UserFormRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFormService {

    private final UserFormRepository userFormRepository;

    @Transactional
    public void saveUserForm(UserForm userForm){
        userFormRepository.save(userForm);
    }

    @Transactional(readOnly = true)
    public Optional<UserForm> getUserForm(User user){
        return userFormRepository.findTop1ByUserOrderByCreatedAtDesc(user);
    }
}
