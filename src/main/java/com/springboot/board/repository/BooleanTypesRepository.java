package com.springboot.board.repository;

import com.springboot.board.entity.BooleanTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooleanTypeRepository extends JpaRepository<BooleanTypes, Long> {
}
