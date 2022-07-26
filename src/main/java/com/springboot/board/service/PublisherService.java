package com.springboot.board.service;

import com.springboot.board.event.RegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PublisherService {

    private final ApplicationEventPublisher publisher;

    public void addLikeWhenBoardPost(Long no) {
        System.out.println("테스트 : " + no);
        publisher.publishEvent(new RegisteredEvent());
    }

}
