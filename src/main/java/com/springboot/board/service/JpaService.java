package com.springboot.board.service;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.entity.Posts;
import com.springboot.board.repository.members.MemberRepository;
import com.springboot.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JpaService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public MemberDto getMemberByNo(long no) {
        List<MemberDto> memberDtoList = memberRepository.findMemberByNo(no);

        if (memberDtoList.isEmpty()) return null;

        return memberDtoList.get(0);
    }

    public Page<MemberDto> getMembersByPage(Pageable pageable) {
        List<MemberDto> memberDtoList = memberRepository.findMembersWithPaging(pageable);

        return new PageImpl<>(memberDtoList, pageable, memberDtoList.size());
    }

    @Transactional
    public void addCount() {
        Posts posts = postRepository.findByIdForUpdate(1L);

        posts.increaseByOne();
        postRepository.save(posts);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseCount() {
        Posts posts = postRepository.findByIdWithOptimisticLock(1L);

        posts.decreaseByOne();

        postRepository.saveAndFlush(posts);
    }
}
