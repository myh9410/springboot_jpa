package com.springboot.board.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.board.dto.BoardDto;
import com.springboot.board.dto.QBoardDto;
import com.springboot.board.entity.Board;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.springboot.board.entity.QBoard.board;

@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BoardCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<BoardDto> findAllBoards() {
        return queryFactory.select(new QBoardDto(board.no, board.title, board.content, board.status))
                .from(board)
                .fetch();
    }

    public BoardDto findBoardByNo(Long no) {
        return queryFactory.select(new QBoardDto(board.no, board.title, board.content, board.status))
                .from(board)
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
