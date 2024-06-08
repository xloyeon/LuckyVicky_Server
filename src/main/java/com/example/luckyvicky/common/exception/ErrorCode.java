package com.example.luckyvicky.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저를 찾지 못했습니다.", "찾는 회원 정보를 다시 확인해주세요.");

    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;
}
