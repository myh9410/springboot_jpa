package com.springboot.board.factory;

import com.springboot.board.entity.Board;
import org.springframework.stereotype.Component;

@Component
public class DefaultBoardFactory {

    public Board getDefaultBoard(long no) {
        return Board.builder()
                .no(no)
                .build();
    }

}
