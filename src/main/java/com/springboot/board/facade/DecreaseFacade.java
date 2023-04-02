package com.springboot.board.facade;

import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DecreaseFacade {

    private JpaService jpaService;

    public void decrease() throws InterruptedException {
        while (true) {
            try {
                jpaService.decreaseCount();

                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }

}
