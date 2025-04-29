package com.handong.finance.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDateTime created_at;


    public User(String username, String password, String name, String phone_Number) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phone_Number;
    }
}
