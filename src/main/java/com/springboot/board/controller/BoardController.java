package com.springboot.board.controller;

import com.springboot.board.dto.request.BoardRequestDto;
import com.springboot.board.dto.BoardResultDto;
import com.springboot.board.entity.Board;
import com.springboot.board.exception.BoardException;
import com.springboot.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 조회")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResultDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardException.class, message = "")
    })
    @GetMapping("/boards/{no}")
    public ResponseEntity<BoardResultDto> getBoard(@PathVariable Long no) {
        try {
            BoardResultDto boardResultDto = boardService.getBoard(no);

            return new ResponseEntity<>(boardResultDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new BoardException(e);
        }
    }

    @ApiOperation(value = "게시글 등록")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResultDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardException.class, message = "")
    })
    @PostMapping(value = "/board")
    public ResponseEntity<BoardResultDto> postBoard(@RequestBody BoardRequestDto boardRequestDto) {
        try {

            Board board = boardService.generateBoardInfo(boardRequestDto);

            BoardResultDto boardResultDto = boardService.createBoard(board);

            return new ResponseEntity<>(boardResultDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new BoardException(e);
        }
    }

    @ApiOperation(value = "게시글 수정")
    @ApiResponses({
            @ApiResponse(code = 200, response = BoardResultDto.class, message = ""),
            @ApiResponse(code = 500, response = BoardException.class, message = "")
    })
    @PutMapping(value = "/boards/{no}")
    public ResponseEntity<BoardResultDto> putBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable Long no) {
        try {
            BoardResultDto boardResultDto = boardService.putBoard(boardRequestDto, no);

            return new ResponseEntity<>(boardResultDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new BoardException(e);
        }
    }

    @ApiOperation(value = "게시글 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, response = ResponseEntity.class, message = "SUCCESS"),
            @ApiResponse(code = 500, response = BoardException.class, message = "")
    })
    @DeleteMapping(value = "/boards/{no}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long no) {
        try {
            boardService.deleteBoard(no);

            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            throw new BoardException(e);
        }
    }

}
