package com.springboot.board.controller;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.service.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JpaController {

    private final JpaService jpaService;

    @GetMapping("/members/{no}")
    public MemberDto getMember(@PathVariable long no) {
        return jpaService.getMemberByNo(no);
    }

}
