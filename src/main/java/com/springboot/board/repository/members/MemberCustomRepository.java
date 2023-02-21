package com.springboot.board.repository.members;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.entity.Members;

import java.util.List;

public interface MemberCustomRepository {

    List<MemberDto> findMemberByNo(long no);
}
