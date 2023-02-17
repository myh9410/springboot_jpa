package com.springboot.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.board.entity.Board;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BoardResponseDto {
    private Long no;

    private String title;

    private String content;

    @JsonIgnore
    private int status;

    @Builder
    public BoardResponseDto(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
