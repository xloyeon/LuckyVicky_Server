package com.example.luckyvicky.card.dto;

import com.example.luckyvicky.card.entity.Animal;
import com.example.luckyvicky.card.entity.Color;
import com.example.luckyvicky.card.entity.Habit;
import com.example.luckyvicky.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateHabitRequest {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;   //제목

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String description; //사건 개요

    private int goal_count;

    private Animal animal;

    private Color color;

    public Habit toEntity(Member member){
        return Habit.builder()
                .member(member)
                .title(title)
                .description(description)
                .goal_count(goal_count)
                .animal(animal)
                .count(0)
                .color(color)
                .build();
    }


}
