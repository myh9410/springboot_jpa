package com.springboot.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.dto.request.BooleanTypesDto;
import com.springboot.board.entity.BooleanTypes;
import com.springboot.board.entity.QBooleanTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class BooleanTypesQueryDslRepository {

    private final QBooleanTypes qBooleanTypes = QBooleanTypes.booleanTypes;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public long update(BooleanTypesDto booleanTypesDto) {
        return jpaQueryFactory.update(qBooleanTypes)
                .set(qBooleanTypes.col_boolean, booleanTypesDto.isVarBool())
                .set(qBooleanTypes.col_enum, booleanTypesDto.getVarEnum())
                .set(qBooleanTypes.col_char, booleanTypesDto.isVarChar())
                .where(qBooleanTypes.no.eq(1L))
                .execute();
    }

}
