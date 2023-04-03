package com.springboot.board.facade;

import com.springboot.board.repository.post.PostRepository;
import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NamedLockFacade {

    private final PostRepository postRepository;
    private final JpaService jpaService;

    @Transactional
    public void decrease() {
        try {
            Integer result = postRepository.getLock(1L);
            System.out.println("get-lock : " + result);
            jpaService.decreaseCountNamed();
        } catch (Exception e) {
            System.out.println("try fail");
            e.printStackTrace();
        } finally {
            Integer result = postRepository.releaseLock(1L);
            System.out.println("release-lock : " + result);
        }
    }

}
