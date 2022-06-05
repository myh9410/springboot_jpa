package com.springboot.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.entity.Board;
import com.springboot.board.entity.QBoard;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BoardQueryDslRepository {

    private final EntityManager entityManager;

    private final QBoard board = QBoard.board;
    private final JPAQueryFactory queryFactory;

    public BoardQueryDslRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Board> findAll() {
        return queryFactory.selectFrom(board)
                .fetch();
    }

}
