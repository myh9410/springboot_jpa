package com.springboot.board.dto;

import com.springboot.board.dto.enums.YorN;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private long no;

    private String userId;

    private LocalDateTime joinDate;

    private YorN active;

    private List<OrderDto> orders = new ArrayList<>();
}
