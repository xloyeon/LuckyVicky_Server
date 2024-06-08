package com.example.luckyvicky.member.service;

import com.example.luckyvicky.common.exception.ErrorCode;
import com.example.luckyvicky.common.exception.LuckyVickyException;
import com.example.luckyvicky.member.entity.Member;
import com.example.luckyvicky.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
