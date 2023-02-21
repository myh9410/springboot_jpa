package com.springboot.board.controller;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
@RestController
public class JpaController {

    private final JpaService jpaService;
    private static final int NUMBER_OF_THREADS = 100;
    private static final ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @GetMapping("/members/{no}")
    public MemberDto getMember(@PathVariable long no) {
        return jpaService.getMemberByNo(no);
    }

    @GetMapping("/multithread/test")
    public void simultaneousTest() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                jpaService.addCount();
                latch.countDown();
            });
        }
        latch.await();

    }

}
