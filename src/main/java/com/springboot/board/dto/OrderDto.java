package com.springboot.board.dto;

import com.querydsl.core.annotations.QueryProjection;
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

//    @QueryProjection
//    public OrderDto(long no, String orderNum, LocalDateTime orderDate, Status status) {
//        this.no = no;
//        this.orderNum = orderNum;
//        this.orderDate = orderDate;
//        this.status = status;
//    }
}
