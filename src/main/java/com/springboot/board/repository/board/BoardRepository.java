package com.springboot.board.repository;

import com.springboot.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.no = :no")
    public Optional<Board> findByNo(@Param("no") Long no);

    public Board save(Board board);
}
