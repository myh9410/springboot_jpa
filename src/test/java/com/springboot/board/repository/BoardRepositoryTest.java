package com.springboot.board.repository;

import com.springboot.board.MockHelper;
import com.springboot.board.config.persistence.DataSourceConfiguration;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(DataSourceConfiguration.class)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("게시판 조회 레포 테스트 - boardRepository.findByNo - optional이 null인 경우")
    @Test
    public void findByNoTestWithNoOptionalResult() {

        //given
        final Board board = MockHelper.getMockBoard().toEntity();

        //when
        Optional<Board> optionalBoard =  boardRepository.findByNo(board.getNo());

        //then
        assertTrue(optionalBoard.isEmpty());

    }

    @DisplayName("게시판 조회 레포 테스트 - boardRepository.findByNo - Board가 정상적으로 조회되는 경우")
    @Test
    public void findByNoTestWithValidOptionalResult() {

        //given
        Board board = Board.builder()
                .no(1L)
                .title("제목1")
                .content("내용1")
                .build();

        //when
        Optional<Board> optionalBoard =  boardRepository.findByNo(board.getNo());

        //then
        assertTrue(optionalBoard.isPresent());
        assertEquals(optionalBoard.get().getNo(), board.getNo());
        assertEquals(optionalBoard.get().getTitle(), board.getTitle());
        assertEquals(optionalBoard.get().getContent(), board.getContent());

    }

    @DisplayName("게시판 추가 레포 테스트 - boardRepository.save")
    @Test
    public void saveTest() {

        //given
        Board beforeBoard = MockHelper.getMockBoard().toEntity();

        //when
        Board afterBoard =  boardRepository.save(beforeBoard);

        //then
        assertEquals(beforeBoard, afterBoard);

    }
}
