package com.handong.finance.dto;

import com.handong.finance.domain.user.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private Integer age;
    private Gender gender;
    private Boolean isBusinessOwner;
}
