package com.springboot.board.entity;

import com.springboot.board.converter.BooleanToEnumConverter;
import com.springboot.board.converter.BooleanToStringConverter;
import com.springboot.board.dto.enums.YorN;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

@Slf4j
@Getter
@DynamicUpdate
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
    @Convert(converter = BooleanToEnumConverter.class)
    @Column(name = "boolean_enum")
    private YorN col_enum;

    @Column(name = "boolean_char")
    @Convert(converter = BooleanToStringConverter.class)
    private boolean col_char;

    @Builder
    public BooleanTypes(long no, boolean inputBool, boolean inputEnum, boolean inputChar) {
        this.no = no;
        this.col_boolean = inputBool;
        this.col_enum = inputEnum ? YorN.Y : YorN.N;
        this.col_char = inputChar;
    }

}