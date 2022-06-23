package com.springboot.board.dto.request;

import com.springboot.board.dto.enums.YorN;
import com.springboot.board.entity.Board;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString
public class BoardRequestDto {

    @ApiModelProperty(value = "게시글 제목", required = true)
    @NotBlank
    private String title;

    @ApiModelProperty(value = "게시글 내용", required = false)
    private String content;

    @ApiModelProperty(value = "비밀게시글 여부", required = false)
    @Valid
    private YorN isPrivate;

    @ApiModelProperty(value = "등록 시간", required = false)
    private LocalDateTime regDate;

    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .isPrivate(this.isPrivate)
                .regDate(this.regDate)
                .build();
    }
}
