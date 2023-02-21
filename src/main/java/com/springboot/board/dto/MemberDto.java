package com.springboot.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.ListPath;
import com.springboot.board.dto.enums.YorN;
import com.springboot.board.entity.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class MemberDto {
    private long no;

    private String userId;

    private LocalDateTime joinDate;

    private YorN active;

    private List<OrderDto> orders = new ArrayList<>();

    @QueryProjection
    public MemberDto(long no, String userId, LocalDateTime joinDate, YorN active, List<Orders> orders) {
        this.no = no;
        this.userId = userId;
        this.joinDate = joinDate;
        this.active = active;

        if (orders != null) {
            this.orders = null;
        }
    }

}
