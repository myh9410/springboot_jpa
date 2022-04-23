package com.springboot.board.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column
    private String title;

    @Setter
    private String content;

    @Builder.Default
    @Setter
    @Column(name = "is_private")
    private Boolean isPrivate = false;

    @CreationTimestamp
    @NotNull
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    private int status;
}
