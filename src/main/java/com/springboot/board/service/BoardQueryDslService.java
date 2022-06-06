package com.springboot.board.service;

import com.springboot.board.dto.BoardResponseDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.repository.BoardQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
