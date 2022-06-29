package com.springboot.board.entity;

import com.springboot.board.dto.enums.YorN;
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
@Table(name = "board")
public class Board {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private long no;

    @Setter
    @Column
    private String title;

    @Setter
    private String content;

    @Setter
    @Column(name = "is_private")
    @Enumerated(EnumType.STRING)
    private YorN isPrivate;

    @CreationTimestamp
    @NotNull
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    private int status;
}