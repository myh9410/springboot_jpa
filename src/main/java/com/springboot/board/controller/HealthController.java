package com.springboot.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HealthController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public Map<String, String> checkHealth() {
        return Map.of("status", "OK");
    }
}
