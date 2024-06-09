package com.example.luckyvicky.member.service;

import com.example.luckyvicky.common.exception.ErrorCode;
import com.example.luckyvicky.common.exception.LuckyVickyException;
import com.example.luckyvicky.member.dto.KakaoTokenResponse;
import com.example.luckyvicky.member.entity.Member;
import com.example.luckyvicky.member.repository.MemberRepository;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

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

    @Transactional
    public Long createMember(String email) {
        Member member = Member.builder()
                .email(email)
                .build();

        memberRepository.save(member);
        return member.getId();
    }
}
