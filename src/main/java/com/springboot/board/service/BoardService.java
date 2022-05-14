package com.springboot.board.service;

import com.springboot.board.dto.BoardResultDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResultDto getBoard(Long no) {

        Optional<Board> optionalBoardInfoDto = boardRepository.findByNo(no);

        optionalBoardInfoDto.ifPresent(board -> BoardResultDto.builder().no(board.getNo()).title(board.getTitle()).content(board.getContent()).build());

        if (optionalBoardInfoDto.isPresent()) {
            return BoardResultDto.builder()
                    .no(no)
                    .title(optionalBoardInfoDto.get().getTitle())
                    .content(optionalBoardInfoDto.get().getContent())
                    .build();
        } else {
            return BoardResultDto.builder()
                    .no(no)
                    .title("")
                    .content("")
                    .build();
        }
    }

    public BoardResultDto createBoard(Board board) {

        Board insertResultBoard = boardRepository.save(board);

        return BoardResultDto.builder()
                .no(insertResultBoard.getNo())
                .title(insertResultBoard.getTitle())
                .content(insertResultBoard.getContent())
                .build();
    }

    public BoardResultDto putBoard(BoardRequestDto boardRequestDto, Long no) {
        Optional<Board> optionalBoard = boardRepository.findByNo(no);

        Board curBoard;

        if (optionalBoard.isPresent()) {
            curBoard = optionalBoard.get();

            curBoard.setTitle(boardRequestDto.getTitle());
            curBoard.setContent(boardRequestDto.getContent());
            curBoard.setIsPrivate(boardRequestDto.getIsPrivate());

            boardRepository.save(curBoard);
        } else {
            throw new NoSuchElementException();
        }

        return BoardResultDto.builder()
                .no(curBoard.getNo())
                .title(curBoard.getTitle())
                .content(curBoard.getContent())
                .build();
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
