package com.springboot.board.entity;

import com.springboot.board.converter.BooleanToStringConverter;
import com.springboot.board.dto.enums.YorN;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "boolean_types")
public class BooleanTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private long no;

    @Column(name = "boolean")
//    @Convert(converter = BooleanToStringConverter.class)
    private boolean col_boolean;

    @Enumerated(EnumType.STRING)
    @Column(name = "boolean_enum")
    private YorN col_enum;

    @Column(name = "boolean_char")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean col_char;

    @Builder
    public BooleanTypes(long no, boolean inputBool, YorN inputEnum, boolean inputChar) {
        this.no = no;
        this.col_boolean = inputBool;
        this.col_enum = inputEnum;
        this.col_char = inputChar;
    }

}