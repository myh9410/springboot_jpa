package com.springboot.board.controller;

import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.dto.BoardResponseDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardNotFoundException;
import com.springboot.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 단일 조회")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResponseDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @GetMapping("/boards/{no}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long no) {
        BoardResponseDto boardResponseDto = boardService.getBoardByNoOrThrowBoardNotFoundException(no);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "전체 게시글 조회")
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = ""),
            @ApiResponse(code = 500, response = List.class, message = "")
    })
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoard() {
        List<BoardResponseDto> list = boardService.getBoards();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "전체 게시글 조회 - querydsl")
    @ApiResponses({
            @ApiResponse(code = 200, response = List.class, message = ""),
            @ApiResponse(code = 500, response = List.class, message = "")
    })
    @GetMapping("/querydsl/boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoardByQueryDsl() {
        List<BoardResponseDto> list = boardService.getBoardsByQueryDsl();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 등록")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResponseDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @PostMapping(value = "/board")
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.generateBoardInfo(boardRequestDto);

        BoardResponseDto boardResponseDto = boardService.createBoard(board);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 단일 수정")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResponseDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardNotFoundException.class, message = "")
    })
    @PutMapping(value = "/boards/{no}")
    public ResponseEntity<BoardResponseDto> putBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable Long no) {
        BoardResponseDto boardResponseDto = boardService.putBoard(boardRequestDto, no);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
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
