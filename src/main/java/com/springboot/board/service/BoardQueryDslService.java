package com.springboot.board.service;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.repository.board.BoardCustomRepositoryImpl;
import com.springboot.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardQueryDslService {

    private final BoardCustomRepositoryImpl boardQueryDslRepository;
    private final BoardRepository boardRepository;

    public BoardDto getBoardByNo(Long no) {
        try {
            return boardQueryDslRepository.findBoardByNo(no);
        } catch (Exception e) {
            throw new BoardNotFoundException("no : " + no + " 게시글을 찾을 수 없습니다.");
        }
    }

    public List<BoardDto> getBoardsByQueryDsl() {
        return boardQueryDslRepository.findAllBoards();
    }

    @Transactional
    public BoardDto createBoard(Board board) {

        Board insertResultBoard = boardRepository.save(board);

        return new BoardDto(insertResultBoard);
    }

    @Transactional
    public BoardDto putBoard(BoardRequestDto boardRequestDto, Long no) {
        long updateNo = boardQueryDslRepository.update(no, boardRequestDto.toEntity());

        return boardQueryDslRepository.findBoardByNo(no);
    }

    @Transactional
    public void deleteBoard(Long no) {
        boardQueryDslRepository.deleteByNo(no);
    }

    public Board generateBoardInfo(BoardRequestDto boardRequestDto) {
        return Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .isPrivate(boardRequestDto.getIsPrivate())
                .regDate(boardRequestDto.getRegDate() != null ? boardRequestDto.getRegDate() : LocalDateTime.now())
                .status(0)
                .build();
    }
}
