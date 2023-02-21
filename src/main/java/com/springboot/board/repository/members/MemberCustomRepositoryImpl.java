package com.springboot.board.repository.members;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.dto.MemberDto;
import com.springboot.board.dto.OrderDto;
import com.springboot.board.dto.QMemberDto;
import com.springboot.board.entity.Members;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.springboot.board.entity.QMembers.members;
import static com.springboot.board.entity.QOrders.orders;

public class MemberCustomRepositoryImpl implements MemberCustomRepository{

    private final JPAQueryFactory queryFactory;

    public MemberCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public List<MemberDto> findMemberByNo(long no) {

        return queryFactory
                .from(members)
                .leftJoin(members.orders, orders)
                .where(members.no.eq(no))
                .transform(
                    groupBy(members.no).list(
                        Projections.fields(
                            MemberDto.class,
                            members.no,
                            members.userId,
                            members.joinDate,
                            members.active,
                            list(Projections.fields(
                                OrderDto.class,
                                orders.no,
                                orders.orderNum,
                                orders.orderDate,
                                orders.status
                            )).as("orders")
                        )
                    )
                );
    }
}
