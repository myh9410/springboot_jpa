package com.springboot.board.controller;

import com.springboot.board.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final PublisherService publisherService;

    @GetMapping("/event/test")
    public void eventWork() {
        Long no = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
        publisherService.addLikeWhenBoardPost(no);
    }

}
