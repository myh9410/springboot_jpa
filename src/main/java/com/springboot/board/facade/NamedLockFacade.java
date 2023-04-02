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
            postRepository.getLock("1");
            jpaService.decreaseCount();
        } finally {
            postRepository.releaseLock("1");
        }
    }

}
