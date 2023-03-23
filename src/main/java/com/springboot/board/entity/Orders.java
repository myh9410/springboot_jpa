package com.springboot.board.entity;

import com.springboot.board.dto.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Members member;

    @Column(name = "order_num")
    private String orderNum;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(STRING)
    private Status status;
}
