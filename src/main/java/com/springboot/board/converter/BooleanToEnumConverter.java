package com.springboot.board.converter;

import com.springboot.board.dto.enums.YorN;
import jakarta.persistence.AttributeConverter;

public class BooleanToEnumConverter implements AttributeConverter<Boolean, YorN> {

    @Override
    public YorN convertToDatabaseColumn(Boolean attribute) {
        return attribute ? YorN.Y : YorN.N;
    }

    @Override
    public Boolean convertToEntityAttribute(YorN dbData) {
        return dbData.name().equals("Y");
    }
}
