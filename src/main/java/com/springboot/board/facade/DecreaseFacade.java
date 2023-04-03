package com.springboot.board.facade;

import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecreaseFacade {

    private final JpaService jpaService;

    //optimistic lock을 사용한 decrease 처리
    public void decrease() throws InterruptedException {
        while (true) {
            try {
                jpaService.decreaseCountOptimistic();

                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }

}
