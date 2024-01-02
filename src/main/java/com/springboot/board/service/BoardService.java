package com.springboot.board.service;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.entity.Members;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public BoardDto getBoardByNoOrThrowBoardNotFoundException(Long no) {
        log.info(String.valueOf(Thread.currentThread().threadId()));

        memberService.findMemberByNo(no);

        Optional<Board> optionalBoardInfoDto = boardRepository.findById(no);

        return new BoardDto(optionalBoardInfoDto.orElseThrow(() -> new BoardNotFoundException("not found")));
    }

    @Transactional(readOnly = true)
    public List<BoardDto> getBoards() {
        log.info(String.valueOf(Thread.currentThread().threadId()));
        List<Board> list =  boardRepository.findAll();

        return list.stream().map(BoardDto::new).collect(Collectors.toList());
    }

    @Transactional
    public BoardDto createBoard(Board board) {

        Board insertResultBoard = boardRepository.save(board);

        return new BoardDto(insertResultBoard);
    }

    @Transactional
    public BoardDto putBoard(BoardRequestDto boardRequestDto, Long no) {
        Optional<Board> optionalBoard = boardRepository.findById(no);

        if (optionalBoard.isEmpty()) throw new BoardNotFoundException("board not found - " + no);

        Board curBoard = optionalBoard.get();
        curBoard.setTitle(boardRequestDto.getTitle());
        curBoard.setContent(boardRequestDto.getContent());
        curBoard.setIsPrivate(boardRequestDto.getIsPrivate());

        Board modifyResultBoard = boardRepository.save(curBoard);

        return new BoardDto(modifyResultBoard);
    }

    @Transactional
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
