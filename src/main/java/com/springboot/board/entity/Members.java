package com.springboot.board.entity;

import com.springboot.board.dto.enums.YorN;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "join_date")
    @CreationTimestamp
    private LocalDateTime joinDate;

    @Enumerated(STRING)
    private YorN active;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Orders> orders = new ArrayList<>();

}
