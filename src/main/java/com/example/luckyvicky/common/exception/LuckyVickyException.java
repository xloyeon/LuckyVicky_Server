package com.example.luckyvicky.common.exception;

import lombok.Getter;

@Getter
public class LuckyVickyException extends RuntimeException{
    private int status;
    private String message;
    private String solution;

    public LuckyVickyException(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getHttpStatus().value();
        this.solution = errorCode.getSolution();
    }

    public LuckyVickyException(ErrorCode errorCode, String message) {
        this.message = message;
        this.status = errorCode.getHttpStatus().value();
        this.solution = errorCode.getSolution();
    }

    public LuckyVickyException(ErrorCode errorCode, String message, String solution) {
        this.message = message;
        this.status = errorCode.getHttpStatus().value();
        this.solution = solution;
    }
}
