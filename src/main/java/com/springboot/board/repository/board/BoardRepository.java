package com.springboot.board.repository.board;

import com.springboot.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
    Board save(Board board);
}
