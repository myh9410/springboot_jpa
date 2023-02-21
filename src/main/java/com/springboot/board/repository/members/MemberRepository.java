package com.springboot.board.repository.members;

import com.springboot.board.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long>, MemberCustomRepository {
}
