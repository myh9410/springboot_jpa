package com.springboot.board.dto;

import com.springboot.board.dto.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long no;

    private String orderNum;

    private LocalDateTime orderDate;

    private Status status;
}
