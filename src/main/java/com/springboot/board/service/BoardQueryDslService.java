package com.springboot.board.service;

import com.springboot.board.dto.BoardResponseDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.repository.BoardQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardQueryDslService {

    private final BoardQueryDslRepository boardQueryDslRepository;

    public BoardResponseDto getBoardByNo(Long no) {
        try {
            Board board = boardQueryDslRepository.findByNo(no);

            return new BoardResponseDto(board);
        } catch (Exception e) {
            throw new BoardNotFoundException("no : " + no + " 게시글을 찾을 수 없습니다.");
        }
    }

    public List<BoardResponseDto> getBoardsByQueryDsl() {
        List<Board> list = boardQueryDslRepository.findAll();

        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto putBoard(BoardRequestDto boardRequestDto, Long no) {
        long updateNo = boardQueryDslRepository.update(no, boardRequestDto.toEntity());

        Board board = boardQueryDslRepository.findByNo(updateNo);

        return new BoardResponseDto(board);
    }

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
