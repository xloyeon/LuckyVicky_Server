package com.example.luckyvicky.member.dto;

import lombok.Data;

@Data
public class KakaoUserInfoResponse {
    private Long id;
    private String connected_at;
    private KakaoAccount kakao_account;
}
