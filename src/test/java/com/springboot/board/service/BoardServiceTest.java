package com.springboot.board.service;

import com.springboot.board.MockHelper;
import com.springboot.board.RandomHelper;
import com.springboot.board.dto.BoardResultDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회 테스트")
    @Test
    public void findBoardByNo() {

        //given
        BoardRequestDto boardRequestDto = MockHelper.getMockBoard();
        Board board = boardService.generateBoardInfo(boardRequestDto);
        given(boardRepository.findByNo(board.getNo())).willReturn(Optional.of(board));

        //when
        boardRepository.findByNo(board.getNo());

        //then
        Mockito.verify(boardRepository, Mockito.times(1)).findByNo(board.getNo());

    }

    @DisplayName("게시판 등록 테스트")
    @Test
    public void postBoard() {
        //given
        BoardRequestDto boardRequestDto = MockHelper.getMockBoard();
        Board board = boardService.generateBoardInfo(boardRequestDto);
        given(boardRepository.save(board)).willReturn(Optional.of(board).get());

        //when
        boardService.createBoard(board);

        //then
        Mockito.verify(boardRepository, Mockito.times(1)).save(board);
    }
}
