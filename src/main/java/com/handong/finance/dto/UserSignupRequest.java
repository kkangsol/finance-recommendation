package com.handong.finance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequest {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
}
