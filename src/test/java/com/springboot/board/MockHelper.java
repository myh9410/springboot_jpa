package com.springboot.board;

import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;

import java.time.LocalDateTime;

public class MockHelper {
    public static BoardRequestDto getMockBoard() {
        return BoardRequestDto.builder()
                .title(RandomHelper.randomString())
                .content(RandomHelper.randomString())
                .regDate(LocalDateTime.now())
                .build();
    }
}
