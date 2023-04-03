package com.springboot.board.controller;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.facade.DecreaseFacade;
import com.springboot.board.facade.NamedLockFacade;
import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final DecreaseFacade decreaseFacade;
    private final NamedLockFacade namedLockFacade;
    private static final int NUMBER_OF_THREADS = 100;
    private static final ExecutorService service = Executors.newFixedThreadPool(32);

    @GetMapping("/members/{no}")
    public MemberDto getMember(@PathVariable long no) {
        return jpaService.getMemberByNo(no);
    }

    @GetMapping("/members")
    public Page<MemberDto> getMember(Pageable pageable) {
        return jpaService.getMembersByPage(pageable);
    }

    @GetMapping("/multithread/pessimistic")
    public void pessimisticTest() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < 100; i++) {
            service.execute(() -> {
                jpaService.addCount();
                latch.countDown();
            });
        }
        latch.await();
    }

    @GetMapping("/multithread/optimistic")
    public void optimisticTest() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        for (int i = 0; i < 30; i++) {
            service.execute(() -> {
                try {
                    decreaseFacade.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
    }

    @GetMapping("/multithread/named")
    public void namedTest() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);

        //9개까지는 잘 도는데 10개부터 잘 안돌아감
        //named lock도 분산 서버 환경에서는 이슈가 생길 수 있음
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                namedLockFacade.decrease();
                latch.countDown();
            });
        }
        latch.await();
    }

}
