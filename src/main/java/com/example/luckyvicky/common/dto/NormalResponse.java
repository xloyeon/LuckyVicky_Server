package com.example.luckyvicky.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NormalResponse {
    private String status;

    protected NormalResponse(String status) {
        this.status = status;
    }

    public static NormalResponse success() {
        return new NormalResponse("SUCCESS");
    }

    public static NormalResponse fail() {
        return new NormalResponse("FAIL");
    }
}
