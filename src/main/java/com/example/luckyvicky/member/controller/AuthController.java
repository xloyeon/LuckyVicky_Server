package com.example.luckyvicky.member.controller;

import com.example.luckyvicky.common.dto.NormalResponse;
import com.example.luckyvicky.member.KakaoTokenJsonData;
import com.example.luckyvicky.member.KakaoUserInfo;
import com.example.luckyvicky.member.dto.KakaoTokenResponse;
import com.example.luckyvicky.member.dto.KakaoUserInfoResponse;
import com.example.luckyvicky.member.entity.Member;
import com.example.luckyvicky.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/kakao")
public class AuthController {

    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final KakaoUserInfo kakaoUserInfo;

    private final MemberService memberService;


    @GetMapping("/auth")
    public ResponseEntity<Map<String, Object>> kakaoOauth(@RequestParam("email") String email) {
        log.info("로그인 요청 들어옴");
        Map<String, Object> response = new HashMap<>();

        Member member = memberService.findByEmail(email);
        if(member==null){
            Long memberId = memberService.createMember(email);
            response.put("memberId", memberId);
        }else{
            response.put("memberId", member.getId());
        }
        return ResponseEntity.ok(response);
    }
}
