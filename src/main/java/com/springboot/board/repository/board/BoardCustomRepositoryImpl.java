package com.springboot.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.entity.Board;
import com.springboot.board.entity.QBoard;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

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

    public Board findByNo(Long no) {
        return queryFactory.selectFrom(board)
                .where(board.no.eq(no))
                .fetchOne();
    }

    public long update(Long no, Board input) {
        return queryFactory.update(board)
                .set(board.title, input.getTitle())
                .set(board.content, input.getContent())
                .set(board.isPrivate, input.getIsPrivate())
                .where(board.no.eq(no))
                .execute();
    }

    public long deleteByNo(Long no) {
        return queryFactory.delete(board)
                .where(board.no.eq(no))
                .execute();
    }

}
