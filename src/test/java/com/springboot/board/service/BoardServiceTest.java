package com.springboot.board.service;

import com.springboot.board.MockHelper;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회 테스트")
    @Test
    public void findBoardByNo() {

        //given
        Board mockBoard = MockHelper.getMockBoard();

        doReturn(
                Optional.of(
                        mockBoard
                )
        ).when(boardRepository).findByNo(mockBoard.getNo());

        //when
        Optional<Board> optionalBoard = boardRepository.findByNo(mockBoard.getNo());

        //then
        optionalBoard.ifPresent(board -> assertEquals(mockBoard.getNo(), board.getNo()));
        optionalBoard.ifPresent(board -> assertEquals(mockBoard.getTitle(), board.getTitle()));
        optionalBoard.ifPresent(board -> assertEquals(mockBoard.getContent(), board.getContent()));
        optionalBoard.ifPresent(board -> assertEquals(mockBoard.getStatus(), board.getStatus()));

    }
}
