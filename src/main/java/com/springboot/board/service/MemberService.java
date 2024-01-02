package com.springboot.board.service;

import com.springboot.board.entity.Members;
import com.springboot.board.repository.members.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Async
    public void findMemberByNo(Long no) {
        log.info(String.valueOf(Thread.currentThread().threadId()));
        memberRepository.findById(no);
    }
}
