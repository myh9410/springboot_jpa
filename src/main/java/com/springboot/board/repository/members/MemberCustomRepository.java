package com.springboot.board.repository.members;

import com.springboot.board.dto.MemberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {

    List<MemberDto> findMemberByNo(long no);

    List<MemberDto> findMembersWithPaging(Pageable pageable);
}
