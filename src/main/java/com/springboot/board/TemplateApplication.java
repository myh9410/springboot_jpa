package com.springboot.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TemplateApplication {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}
