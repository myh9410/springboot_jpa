package com.springboot.board.controller;

import com.springboot.board.dto.BoardResultDto;
import com.springboot.board.exception.BoardException;
import com.springboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/{no}")
    public ResponseEntity<BoardResultDto> getBoard(@PathVariable String no) {
        try {
            BoardResultDto boardResultDto = boardService.getBoard(no);

            return new ResponseEntity<>(boardResultDto, HttpStatus.OK);
        } catch (Exception ex) {
            throw new BoardException(ex);
        }
    }

}
