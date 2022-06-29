package com.springboot.board.dto.request;


import com.springboot.board.dto.enums.YorN;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@ToString
public class BooleanTypesDto {

    private boolean varBool;

    private YorN varEnum;

    private boolean varChar;

}
