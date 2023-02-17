package com.springboot.board.repository;

import com.springboot.board.MockHelper;
import com.springboot.board.config.persistence.DataSourceConfiguration;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.board.BoardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest //@SpringBootTest는 실제 DataSourceConfiguration을 활용하여 테스트를 진행하게 된다.
@Transactional  //실제 DB에 데이터가 추가,수정,삭제 되는 것을 방지하기 위하여 @Transactional을 추가한다.
@Import(DataSourceConfiguration.class)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

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

    @DisplayName("게시판 삭제 레포 테스트 - boardRepository.deleteById")
    @Test
    public void deleteTest() {
        //given
        Board board = MockHelper.getMockBoard().toEntity();
        Board afterBoard = boardRepository.save(board);
        Long no = afterBoard.getNo();

        //when
        boardRepository.deleteById(no);

        //then
        assertFalse(boardRepository.existsById(no));
    }
}
