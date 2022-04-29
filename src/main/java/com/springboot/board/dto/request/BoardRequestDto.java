package com.springboot.board.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @ApiModelProperty(value = "비밀게시글 여부", required = true)
    @Valid
    private Boolean isPrivate;
}
