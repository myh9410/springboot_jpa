package com.springboot.board.service;

import com.springboot.board.dto.BoardResultDto;
import com.springboot.board.entity.Board;
import com.springboot.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResultDto getBoard(String no) {

        Optional<Board> optionalBoardInfoDto = boardRepository.findByNo(Long.parseLong(no));

        optionalBoardInfoDto.ifPresent(board -> BoardResultDto.builder().no(board.getNo()).title(board.getTitle()).content(board.getContent()).build());

        if (optionalBoardInfoDto.isPresent()) {
            return BoardResultDto.builder()
                    .no(Long.parseLong(no))
                    .title(optionalBoardInfoDto.get().getTitle())
                    .content(optionalBoardInfoDto.get().getContent())
                    .build();
        } else {
            return BoardResultDto.builder()
                    .no(Long.parseLong(no))
                    .title("")
                    .content("")
                    .build();
        }
    }
}
