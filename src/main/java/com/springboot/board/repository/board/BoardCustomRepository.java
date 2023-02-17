package com.springboot.board.repository.board;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.entity.Board;

import java.util.List;

public interface BoardCustomRepository {
    List<BoardDto> findAllBoards();
    BoardDto findBoardByNo(Long no);

    long update(Long no, Board inputBoard);

    long deleteByNo(Long no);

}
