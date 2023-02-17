package com.springboot.board.controller;

import com.springboot.board.dto.BoardDto;
import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.service.BoardQueryDslService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("querydsl")
public class BoardQueryDslController {

    private final BoardQueryDslService boardService;

    @ApiOperation(value = "게시글 단일 조회")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @GetMapping("/boards/{no}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long no) {
        BoardDto boardDto = boardService.getBoardByNo(no);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "전체 게시글 조회 - querydsl")
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = ""),
            @ApiResponse(code = 500, response = List.class, message = "")
    })
    @GetMapping("/boards")
    public ResponseEntity<List<BoardDto>> getAllBoardByQueryDsl() {
        List<BoardDto> list = boardService.getBoardsByQueryDsl();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 등록")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @PostMapping(value = "/board")
    public ResponseEntity<BoardDto> postBoard(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.generateBoardInfo(boardRequestDto);

        BoardDto boardDto = boardService.createBoard(board);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 단일 수정")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @PutMapping(value = "/boards/{no}")
    public ResponseEntity<BoardDto> putBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable Long no) {
        BoardDto boardDto = boardService.putBoard(boardRequestDto, no);

        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, response = ResponseEntity.class, message = "SUCCESS"),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @DeleteMapping(value = "/boards/{no}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long no) {
        boardService.deleteBoard(no);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
