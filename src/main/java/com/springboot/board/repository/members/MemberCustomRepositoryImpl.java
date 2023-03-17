package com.springboot.board.repository.members;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.dto.MemberDto;
import com.springboot.board.dto.OrderDto;
import com.springboot.board.entity.QMembers;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.springboot.board.entity.QMembers.members;
import static com.springboot.board.entity.QOrders.orders;
import static com.springboot.board.config.persistence.QueryDslUtil.*;

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
                            ).skipNulls()).as("orders")
                        )
                    )
                );
    }

    @Override
    public List<MemberDto> findMembersWithPaging(Pageable pageable) {

        List<OrderSpecifier> ORDERS = getAllOrderSpecifiersOfMember(pageable);

        return queryFactory
                .from(members)
                .offset(pageable.getOffset()+1)
                .limit(pageable.getPageSize())
                .leftJoin(members.orders, orders)
                .orderBy(ORDERS.toArray(OrderSpecifier[]::new))
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
                                        ).skipNulls()).as("orders")
                                )
                        )
                );
    }


    private List<OrderSpecifier> getAllOrderSpecifiersOfMember(Pageable pageable) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "no":
                        ORDERS.add(getSortedColumn(direction, QMembers.members, "no"));
                        break;

                    case "user_id":
                        ORDERS.add(getSortedColumn(direction, QMembers.members, "user_id"));
                        break;

                    case "join_date":
                        ORDERS.add(getSortedColumn(direction, QMembers.members, "join_date"));

                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }
}
