package com.springboot.board.service;

import com.springboot.board.MockHelper;
import com.springboot.board.RandomHelper;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회 서비스 테스트 - service layer에서 repository.findByNo 정상 호출 테스트")
    @Test
    public void findBoardByNoServiceTest() {

        //given
        Long no = RandomHelper.randomLong();
        BoardRequestDto boardRequestDto = MockHelper.getMockBoard();
        Board board = generateBoardByMockRequestDto(boardRequestDto);
        given(boardRepository.findByNo(no)).willReturn(Optional.of(board));

        //when
        boardService.getBoardByNoOrThrowBoardNotFoundException(no);

        //then
        Mockito.verify(boardRepository, Mockito.times(1)).findByNo(no);

    }

    @DisplayName("게시판 생성 테스트 - service layer에서 repository.save 정상 호출 테스트")
    @Test
    public void createBoardServiceTest() {

        //given
        BoardRequestDto boardRequestDto = MockHelper.getMockBoard();
        Board board = boardRequestDto.toEntity();
        given(boardRepository.save(board)).willReturn(board);

        //when
        boardService.createBoard(board);

        //then
        Mockito.verify(boardRepository, Mockito.times(1)).save(board);
    }

    private Board generateBoardByMockRequestDto(BoardRequestDto boardRequestDto) {
        return Board.builder()
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .isPrivate(boardRequestDto.getIsPrivate())
                .regDate(boardRequestDto.getRegDate())
                .status(0)
                .build();
    }
}
