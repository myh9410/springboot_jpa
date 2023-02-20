package com.springboot.board.controller;

import com.springboot.board.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/async")
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping("/not-same-thread")
    public void notSameThreadAsync() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().threadId()+":controller");
        for (int i = 1; i <= 10; i++) {
            asyncService.asyncTest(i);
        }
    }

    @GetMapping("/same-thread")
    public void sameThreadAsync() throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName());
            asyncService.asyncTest(i);
        }
    }

}
