package com.springboot.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BoardResultDto {
    private Long no;

    private String title;

    private String content;

    @JsonIgnore
    private int status;

    @Builder
    public BoardResultDto(Long no, String title, String content) {
        this.no = no;
        this.title = title;
        this.content = content;
    }
}
