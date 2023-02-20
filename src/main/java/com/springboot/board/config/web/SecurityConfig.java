package com.springboot.board.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Profile("!test")
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .httpBasic().disable()

                .cors().and()

                .authorizeHttpRequests()

                .requestMatchers(
                        "/health", "/board/**","/boards/**","/querydsl/**","/async/**",
                        "/swagger-ui/**","/swagger-resources/**","/webjars/**","/v2/api-docs",
                        "/swagger-ui.html","/configuration/ui","/swagger-resources","/configuration/security","/swagger/**"
                )
                .permitAll()
        ;

        return http.build();
    }
}
