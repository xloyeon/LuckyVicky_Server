package com.example.luckyvicky.member.controller;

import com.example.luckyvicky.common.dto.NormalResponse;
import com.example.luckyvicky.member.KakaoTokenJsonData;
import com.example.luckyvicky.member.KakaoUserInfo;
import com.example.luckyvicky.member.dto.KakaoTokenResponse;
import com.example.luckyvicky.member.dto.KakaoUserInfoResponse;
import com.example.luckyvicky.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/kakao")
public class AuthController {
    @Value("${kakao.client_id}")
    String clientId;
    @Value("${kakao.redirect_uri}")
    String redirectUri;
    @Value("${kakao.client_secret}")
    String clientSecret;

    private final KakaoTokenJsonData kakaoTokenJsonData;
    private final KakaoUserInfo kakaoUserInfo;

    private final MemberService memberService;


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<NormalResponse> kakaoOauth(@RequestParam("code") String code) {
        KakaoTokenResponse kakaoTokenResponse = kakaoTokenJsonData.getToken(code);
        KakaoUserInfoResponse userInfo = kakaoUserInfo.getUserInfo(kakaoTokenResponse.getAccess_token());
        memberService.createMember(userInfo.getKakao_account().getEmail());

        return ResponseEntity.ok(NormalResponse.success());
    }
}
