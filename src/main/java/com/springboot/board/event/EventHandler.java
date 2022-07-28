package com.springboot.board.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @EventListener
    public void addLike(RegisteredEvent event) {
        System.out.println(event.getName() + "addLike");
    }


}
