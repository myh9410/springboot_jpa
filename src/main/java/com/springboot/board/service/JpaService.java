package com.springboot.board.service;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.entity.Posts;
import com.springboot.board.repository.members.MemberRepository;
import com.springboot.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
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

    //@Synchronized - 특정 필드의 락을 걸어 보호 / 하나의 프로세스 내에서만 동시성 보장
    //분산 서버 환경에서는 @Synchronized로 동시성을 보장할 수 없으므로, DB의 Lock을 통한 동시성 보장 / mq를 통한 동시성 보장을 활용한다.
    @Transactional
    public void addCount() {
        Posts posts = postRepository.findByIdWithPessimisticLock(1L);

        posts.increaseByOne();
        postRepository.save(posts);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseCountNamed() {
        Posts posts = postRepository.findByIdForUpdate(1L);

        posts.decreaseByOne();

        postRepository.saveAndFlush(posts);
    }

    @Transactional
    public void decreaseCountOptimistic() {
        Posts posts = postRepository.findByIdWithOptimisticLock(1L);

        posts.decreaseByOne();

        postRepository.saveAndFlush(posts);
    }
}
