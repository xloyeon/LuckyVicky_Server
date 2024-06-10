package com.example.luckyvicky.card.dto;

import com.example.luckyvicky.card.entity.Animal;
import com.example.luckyvicky.card.entity.Color;
import com.example.luckyvicky.card.entity.Habit;
import com.example.luckyvicky.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateHabitRequest {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;   //제목

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String description; //사건 개요

    private int count;

    private Color color;
}
