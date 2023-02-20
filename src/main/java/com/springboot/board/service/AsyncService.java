package com.springboot.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    @Async(value = "taskExecutor")
    public void asyncTest(int val) throws InterruptedException {
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName()+":"+Thread.currentThread().threadId()+":"+val);
    }

}
