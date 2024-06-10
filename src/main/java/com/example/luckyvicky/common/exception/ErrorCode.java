package com.example.luckyvicky.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저를 찾지 못했습니다.", "찾는 회원 정보를 다시 확인해주세요."),
    VALUE_NOT_IN_OPTION(HttpStatus.BAD_REQUEST, "선택지에 없는 값을 사용했습니다.", "선택지에 있는 값을 사용해야 합니다.");

    private final HttpStatus httpStatus;
    private final String message;
    private final String solution;
}
