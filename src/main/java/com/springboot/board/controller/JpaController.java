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
    private static final ExecutorService service = Executors.newFixedThreadPool(10);

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

        /**
         * connection-pool로 인한 Dead Lock 발생건
         *
         * 이슈 : default connection pool size로 설정했을 때 아래 코드가 1~9까지는 잘 도는데 1~10부터는 안돌고 계속 pending 걸려있음
         *
         * 원인 :
         * hikari default pool size가 10 > multi-thread에서 각 thread마다 get_lock으로 connection 1개씩 사용해서 pool size를 다 사용하게 됨.
         * lock을 선점한 thread에서 데이터에 대한 수정 - decreaseCountNamed()을 해야되는데 남은 connection이 connection pool에 없음.
         * >> 계속 pending 걸려잇다가 CannotCreateTransactionException 발생
         *      HikariPool-1 - Pool stats (total=10, active=10, idle=0, waiting=1) -> 왜 waiting이 1개 있지?
         *      CannotCreateTransactionException : Could not open JPA EntityManager for transaction
         * Pool-Size = 최대 쓰레드 수 * (하나의 쓰레드가 작업을 수행하기 위해 필요한 DB 커넥션 수 -1) + 1 만큼을 권장한다.
         *
         * 해결 :
         * @ConfigurationProperties(prefix = "spring.datasource.hikari") DataSourceConfiguration에 property로
         * maximum-pool-size로 최대 늘어날 수 있는 pool-size를 위 공식 참고하여 설정해줌
         *
         */
        //named lock도 분산 서버 환경에서는 이슈가 생길 수 있음
        for (int i = 1; i <= 10; i++) {
            service.execute(() -> {
                namedLockFacade.decrease();
                latch.countDown();
            });
        }
        latch.await();
    }

}
