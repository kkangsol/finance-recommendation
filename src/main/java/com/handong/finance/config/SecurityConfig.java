package com.handong.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable()) // ✅ 최신 버전 방식으로 변경
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll() // 모든 요청 허용
                )
                .formLogin((formLogin) -> formLogin.disable()); // 기본 로그인 폼 비활성화

        return http.build();
    }
}
