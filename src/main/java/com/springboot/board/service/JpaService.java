package com.springboot.board.service;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.repository.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JpaService {

    private final MemberRepository memberRepository;

    public MemberDto getMemberByNo(long no) {
        List<MemberDto> memberDtoList = memberRepository.findMemberByNo(no);

        if (memberDtoList.isEmpty()) return null;

        return memberDtoList.get(0);
    }

}
