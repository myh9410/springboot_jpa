package com.springboot.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBooleanTypes is a Querydsl query type for BooleanTypes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooleanTypes extends EntityPathBase<BooleanTypes> {

    private static final long serialVersionUID = -354363374L;

    public static final QBooleanTypes booleanTypes = new QBooleanTypes("booleanTypes");

    public final BooleanPath col_boolean = createBoolean("col_boolean");

    public final BooleanPath col_char = createBoolean("col_char");

    public final EnumPath<com.springboot.board.dto.enums.YorN> col_enum = createEnum("col_enum", com.springboot.board.dto.enums.YorN.class);

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public QBooleanTypes(String variable) {
        super(BooleanTypes.class, forVariable(variable));
    }

    public QBooleanTypes(Path<? extends BooleanTypes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBooleanTypes(PathMetadata metadata) {
        super(BooleanTypes.class, metadata);
    }

}

