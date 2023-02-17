package com.springboot.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import com.springboot.board.entity.Board;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardDto {
    private Long no;

    private String title;

    private String content;

    @JsonIgnore
    private int status;

    @Builder
    public BoardDto(Board board) {
        this.no = board.getNo();
        this.title = board.getTitle();
        this.content = board.getContent();
    }

    @QueryProjection
    public BoardDto(long no, String title, String content, int status) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
