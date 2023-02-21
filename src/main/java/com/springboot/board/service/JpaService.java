package com.springboot.board.service;

import com.springboot.board.dto.MemberDto;
import com.springboot.board.entity.Posts;
import com.springboot.board.repository.members.MemberRepository;
import com.springboot.board.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public void addCount() {
        Optional<Posts> optionalPosts = postRepository.findById(1L);

        if (optionalPosts.isEmpty()) throw new NullPointerException("null error");

        Posts posts = optionalPosts.get();
        long likes = posts.getLikes();
        posts.setLikes(likes+1);
        postRepository.save(posts);
    }

}
