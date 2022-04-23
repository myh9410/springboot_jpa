package com.springboot.board;

import com.springboot.board.entity.Board;

public class MockHelper {
    public static Board getMockBoard() {
        return Board.builder()
                .no(RandomHelper.randomLong())
                .title(RandomHelper.randomString())
                .content(RandomHelper.randomString())
                .status(RandomHelper.randomInt())
                .build();

    }
}
