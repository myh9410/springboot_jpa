package com.springboot.board.service;

import com.springboot.board.dto.BoardResponseDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.repository.BoardQueryDslRepository;
import com.springboot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto getBoardByNoOrThrowBoardNotFoundException(Long no) {

        Optional<Board> optionalBoardInfoDto = boardRepository.findByNo(no);

        return new BoardResponseDto(optionalBoardInfoDto.orElseThrow(() -> new BoardNotFoundException("not found")));
    }

    public List<BoardResponseDto> getBoards() {

        List<Board> list =  boardRepository.findAll();

        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto createBoard(Board board) {

        Board insertResultBoard = boardRepository.save(board);

        return new BoardResponseDto(insertResultBoard);
    }

    public BoardResponseDto putBoard(BoardRequestDto boardRequestDto, Long no) {
        Optional<Board> optionalBoard = boardRepository.findByNo(no);

        Board curBoard = optionalBoard.orElseThrow(() -> new BoardNotFoundException("nothing to update"));

        curBoard.setTitle(boardRequestDto.getTitle());
        curBoard.setContent(boardRequestDto.getContent());
        curBoard.setIsPrivate(boardRequestDto.getIsPrivate());

        Board modifyResultBoard = boardRepository.save(curBoard);

        return new BoardResponseDto(modifyResultBoard);
    }

    public void deleteBoard(Long no) {
        boardRepository.deleteById(no);
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
