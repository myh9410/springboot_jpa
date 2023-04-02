package com.springboot.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Setter
    private long likes;

    private String title;

    public void decreaseByOne() {
        if (this.likes -1 < 0) {
            throw new RuntimeException("감소할 수 없습니다. (likes 0 이하)");
        }

        this.likes -= 1;
    }

    public void increaseByOne() {
        this.likes +=1;
    }
}
