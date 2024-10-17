package com.concertreservation.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 새로운 방식으로 CSRF 보호 비활성화
            .authorizeRequests(authorizeRequests -> 
                authorizeRequests
                    .anyRequest().authenticated()
            );

        return http.build();
    }
}
